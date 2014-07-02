package net.aimeizi.spring.testng.example.service;

import net.aimeizi.spring.testng.example.common.exceptions.NonExistentUserException;
import net.aimeizi.spring.testng.example.model.User;

import java.util.Collection;

/**
 * Created by 冯靖 on 2014/7/1 0001.
 */
public interface IUserService {

    /**
     * Adds User
     *
     * @param  user
     * @return boolean whether delete operation is success or not.
     */
    boolean addUser(User user);

    /**
     * Deletes User
     *
     * @param  user
     * @return boolean whether delete operation is success or not.
     */
    boolean deleteUser(User user);

    /**
     * Updates User
     *
     * @param  user
     * @throws NonExistentUserException
     */
    void updateUser(User user) throws NonExistentUserException;

    /**
     * Gets User
     *
     * @param  id
     * @return User
     */
    User getUserById(String id);

    /**
     * Gets User Collection
     *
     * @return List - User list
     */
    Collection<User> getUsers();
}