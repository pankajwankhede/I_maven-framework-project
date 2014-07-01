package net.aimeizi.rmi.cache.service;

import java.util.concurrent.ConcurrentHashMap;

import net.aimeizi.rmi.model.User;

public interface ICacheService {
 
    /**
     * Get User Map
     *
     * @return ConcurrentHashMap User Map
     */
    public ConcurrentHashMap<Long, User> getUserMap();
 
}
