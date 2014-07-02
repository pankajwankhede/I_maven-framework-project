package net.aimeizi.spring.testng.example.cache.service;

import net.aimeizi.spring.testng.example.model.User;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 冯靖 on 2014/7/1 0001.
 */
public interface ICacheService {

    /**
     * Gets User Map
     *
     * @return ConcurrentHashMap User Map
     */
    ConcurrentHashMap<String, User> getUserMap();
}
