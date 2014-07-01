package net.aimeizi.rmi.httpinvoke.example.http.server;

import java.util.ArrayList;
import java.util.List;

import net.aimeizi.rmi.httpinvoke.example.cache.service.ICacheService;
import net.aimeizi.rmi.httpinvoke.example.model.User;

import org.apache.log4j.Logger;

public class HttpUserService implements IHttpUserService {
	 
    private static Logger logger = Logger.getLogger(HttpUserService.class);
 
    ICacheService cacheService;
 
    /**
     * Add User
     *
     * @param  User user
     * @return boolean response of the method
     */
    public boolean addUser(User user) {
        getCacheService().getUserMap().put(user.getId(), user);
        logger.debug("User has been added to cache. User : "+getCacheService().getUserMap().get(user.getId()));
        return true;
    }
 
    /**
     * Delete User
     *
     * @param  User user
     * @return boolean response of the method
     */
    public boolean deleteUser(User user) {
        getCacheService().getUserMap().remove(user.getId());
        logger.debug("User has been deleted from cache. User : "+user);
        return true;
    }
 
    /**
     * Get User List
     *
     * @return List user list
     */
    public List<User> getUserList() {
        List<User> list = new ArrayList<User>();
        list.addAll(getCacheService().getUserMap().values());
        logger.debug("User List : "+list);
        return list;
    }
 
    /**
     * Get Remote Cache Service
     *
     * @return ICacheService Remote Cache Service
     */
    public ICacheService getCacheService() {
        return cacheService;
    }
 
    /**
     * Set Remote Cache Service
     *
     * @param ICacheService Remote Cache Service
     */
    public void setCacheService(ICacheService cacheService) {
        this.cacheService = cacheService;
    }
 
}
