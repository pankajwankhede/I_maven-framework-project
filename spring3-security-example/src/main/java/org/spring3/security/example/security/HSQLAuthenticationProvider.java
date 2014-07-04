package org.spring3.security.example.security;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.spring3.security.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("hsqlAuthenticationProvider")
public class HSQLAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider{

    private static final Logger log = Logger.getLogger(HSQLAuthenticationProvider.class);

    protected DataSource ds;
    protected NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource ds){
        log.trace("setDateSource()");
        this.ds = ds;
        this.jdbcTemplate = new NamedParameterJdbcTemplate(ds);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.trace("authenticate()");
        log.debug("authenticating: " + authentication);
        String clearText = String.valueOf(authentication.getCredentials());
        UserDetails userDetails = this.retrieveUser(authentication.getName(), (UsernamePasswordAuthenticationToken) authentication);

        if (!StringUtils.equals(clearText, userDetails.getPassword())){
            throw new BadCredentialsException("invalid password");
        }
        if (!userDetails.isEnabled()){
            throw new BadCredentialsException("User not enabled");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        log.trace("additionalAuthenticationChecks()");
        log.debug("isEnabled: " + userDetails.isEnabled());
        if (!userDetails.isEnabled()){
            throw new BadCredentialsException("User not enabled");
        }
    }

    @Override
    @Transactional(readOnly = true)
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        log.trace("retrieveUser()");
        log.debug("retrieving user: " + username);
        User user;
        try {
             user= this.read(username);
        }
        catch(Exception e){
            throw new UsernameNotFoundException("User " + username + " cannot be found");
        }

        String userName = user.getName();
        String pw = user.getPassword();
        String role = user.getRole();
        Collection<GrantedAuthority> auths = AuthorityUtils.createAuthorityList(role);
        boolean enabled = user.getActive();

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(userName, pw, enabled, true, true, true, auths);
        log.debug("returning new userDetails: " + userDetails);
        return userDetails;
    }

    @Transactional(readOnly = true)
    protected User read(String name) {
        log.trace("read()");
        log.debug("reading user: " + name);
        String sql = "SELECT users.id, users.name, users.active, users.password, roles.name FROM users, roles " +
                " WHERE roles.id = users.role AND" +
                " UPPER(users.name) = :name";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", StringUtils.upperCase(name));
        User user = this.jdbcTemplate.queryForObject(sql, params, new UserRowMapper());
        return user;
    }

    private class UserRowMapper implements RowMapper<org.spring3.security.example.model.User>{
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new org.spring3.security.example.model.User();
            user.setName(StringUtils.trim(rs.getString("users.name")));
            user.setId(StringUtils.trim(rs.getString("users.id")));
            user.setPassword(StringUtils.trim(rs.getString("users.password")));
            user.setRole(StringUtils.trim(rs.getString("roles.name")));
            user.setActive(rs.getBoolean("users.active"));
            log.trace(user.toString());
            return user;
        }

    }


}


