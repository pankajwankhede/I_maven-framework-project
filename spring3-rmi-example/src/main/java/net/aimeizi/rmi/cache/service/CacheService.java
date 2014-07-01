package net.aimeizi.rmi.cache.service;

import java.util.concurrent.ConcurrentHashMap;

import net.aimeizi.rmi.model.User;

public class CacheService implements ICacheService {

    ConcurrentHashMap<Long, User> userMap;
 
    /**
     * Get User Map
     *
     * @return ConcurrentHashMap User Map
     */
    public ConcurrentHashMap<Long, User> getUserMap() {
        return userMap;
    }
 
    /**
     * Set User Map
     *
     * @param ConcurrentHashMap User Map
     */
    public void setUserMap(ConcurrentHashMap<Long, User> userMap) {
        this.userMap = userMap;
    }

}
