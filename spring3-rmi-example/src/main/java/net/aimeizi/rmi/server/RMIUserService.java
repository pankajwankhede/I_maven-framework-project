package net.aimeizi.rmi.server;

import java.util.ArrayList;
import java.util.List;

import net.aimeizi.rmi.cache.service.ICacheService;
import net.aimeizi.rmi.model.User;

import org.apache.log4j.Logger;

public class RMIUserService implements IRMIUserService {

	private static Logger logger = Logger.getLogger(RMIUserService.class);
	 
    //Remote Cache Service is injected...
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
        getCacheService().getUserMap().put(user.getId(), user);
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
     * Get RMI User Service
     *
     * @return IRMIUserService RMI User Service
     */
    public ICacheService getCacheService() {
        return cacheService;
    }
 
    /**
     * Set RMI User Service
     *
     * @param IRMIUserService RMI User Service
     */
    public void setCacheService(ICacheService cacheService) {
        this.cacheService = cacheService;
    }

}
