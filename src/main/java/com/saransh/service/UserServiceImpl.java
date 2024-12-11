package com.saransh.service;

import com.saransh.dao.UserDAO;
import com.saransh.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;



@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    @Override
    @Transactional
    public void addUser(User user) {
    	userDAO.addUser(user);
    }

    @Transactional
    public User getUser(int id) {
        return userDAO.getUser(id);
    }

    @Override
    @Transactional
    public List<User> getUsers(int sortBy) {
        return userDAO.getUsers(sortBy);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
    	userDAO.deleteUser(id);
    }

    @Override
    @Transactional
    public List<User> searchUser(String searchString) {
        return userDAO.searchUser(searchString);
    }
    
    @Override
    @Transactional
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
