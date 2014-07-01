package net.aimeizi.rmi.httpinvoke.example.cache.service;

import java.util.concurrent.ConcurrentHashMap;

import net.aimeizi.rmi.httpinvoke.example.model.User;

public interface ICacheService {
	 
    /**
     * Get User Map
     *
     * @return ConcurrentHashMap User Map
     */
    public ConcurrentHashMap<Long, User> getUserMap();
 
}
