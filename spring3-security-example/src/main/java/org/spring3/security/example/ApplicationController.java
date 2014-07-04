package org.spring3.security.example;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

@Controller
public class ApplicationController {
    private static final Logger log = Logger.getLogger(ApplicationController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index.htm", method = RequestMethod.GET)
    public String index(ModelMap map) {
        log.debug("index.htm");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails =
                (UserDetails) authentication.getPrincipal();
        map.addAttribute("userDetails", userDetails);
        Collection<? extends GrantedAuthority> roles = userService.getAuthorities(userDetails);
        map.addAttribute("userDetails", userDetails);
        map.addAttribute("userAuthorities", roles);

        return "index";
    }

    @RequestMapping(value = "/admin.htm", method = RequestMethod.GET)
    public String admin(ModelMap map) {
        log.debug("admin.htm");
        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Collection<? extends GrantedAuthority> roles = userService.getAuthorities(userDetails);
        map.addAttribute("userDetails", userDetails);
        map.addAttribute("userAuthorities", roles);
        return "admin";
    }
}
