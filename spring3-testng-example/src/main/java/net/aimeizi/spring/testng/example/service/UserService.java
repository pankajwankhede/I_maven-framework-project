package net.aimeizi.spring.testng.example.service;

import net.aimeizi.spring.testng.example.cache.service.ICacheService;
import net.aimeizi.spring.testng.example.common.exceptions.NonExistentUserException;
import net.aimeizi.spring.testng.example.model.User;

import java.util.Collection;

/**
 * Created by 冯靖 on 2014/7/1 0001.
 */
public class UserService implements IUserService {

    //CacheService is injected...
    private ICacheService cacheService;

    /**
     * Adds User
     *
     * @param  user
     * @return boolean whether delete operation is success or not.
     */
    public boolean addUser(User user) {

        getCacheService().getUserMap().put(user.getId(), user);
        if(getCacheService().getUserMap().get(user.getId()).equals(user)) {
            return true;
        }

        return false;
    }

    /**
     * Deletes User
     *
     * @param  user
     * @return boolean whether delete operation is success or not.
     */
    public boolean deleteUser(User user) {
        User removedUser = getCacheService().getUserMap().remove(user.getId());
        if(removedUser != null) {
            return true;
        }
        return false;
    }

    /**
     * Updates User
     *
     * @param  user
     * @throws NonExistentUserException
     */
    public void updateUser(User user) throws NonExistentUserException {
        if(getCacheService().getUserMap().containsKey(user.getId())) {
            getCacheService().getUserMap().put(user.getId(), user);
        } else {
            throw new NonExistentUserException("Non Existent User can not update! User : "+user);
        }
    }

    /**
     * Gets User
     *
     * @param  id
     * @return User
     */
    public User getUserById(String id) {
        return getCacheService().getUserMap().get(id);
    }

    /**
     * Gets User List
     *
     * @return Collection - Collection of Users
     */
    public Collection<User> getUsers() {
        return (Collection<User>) getCacheService().getUserMap().values();
    }

    /**
     * Gets Cache Service
     *
     * @return ICacheService - Cache Service
     */
    public ICacheService getCacheService() {
        return cacheService;
    }

    /**
     * Sets Cache Service
     *
     * @param cacheService
     */
    public void setCacheService(ICacheService cacheService) {
        this.cacheService = cacheService;
    }

}