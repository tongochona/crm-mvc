package com.saransh.dao;

import com.saransh.entity.User;

import java.util.List;


public interface UserDAO {

   
    void addUser(User user);

    User getUser(int id);

    
    List<User> getUsers(int sortBy);

    
    void deleteUser(int id);

    
    List<User> searchUser(String searchString);
    
    User findByUsername(String username);
}
