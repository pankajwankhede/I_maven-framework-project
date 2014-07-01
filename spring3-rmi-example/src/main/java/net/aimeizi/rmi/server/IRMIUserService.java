package net.aimeizi.rmi.server;

import java.util.List;

import net.aimeizi.rmi.model.User;

public interface IRMIUserService {
	
	/**
     * Add User
     *
     * @param  User user
     * @return boolean response of the method
     */
    public boolean addUser(User user);
 
    /**
     * Delete User
     *
     * @param  User user
     * @return boolean response of the method
     */
    public boolean deleteUser(User user);
 
    /**
     * Get User List
     *
     * @return List user list
     */
    public List<User> getUserList();
	
}
