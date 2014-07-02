package net.aimeizi.spring.testng.example.cache.service;

import net.aimeizi.spring.testng.example.model.User;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 冯靖 on 2014/7/1 0001.
 */
public class CacheService implements ICacheService {

    //User Map is injected...
    private ConcurrentHashMap<String, User> userMap;

    /**
     * Gets User Map
     *
     * @return ConcurrentHashMap User Map
     */
    public ConcurrentHashMap<String, User> getUserMap() {
        return userMap;
    }

    /**
     * Sets User Map
     *
     * @param userMap
     */
    public void setUserMap(ConcurrentHashMap<String, User> userMap) {
        this.userMap = userMap;
    }

}