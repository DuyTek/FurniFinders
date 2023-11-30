package com.furnifinders.backend.service;

import com.furnifinders.backend.DAO.UserDAO;
import com.furnifinders.backend.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User addUser(User user) {
        userDAO.addUser(user);
        return user;
    }

    @Override
    public User getUserByID(int id) {
        return userDAO.getUserByID(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    @Override
    public User getUserByPhone(String phone) {
        return userDAO.getUserByPhone(phone);
    }

    @Override
    public List<User> getAllUserByRole(String role) {
        return userDAO.getAllUserByRole(role);
    }

    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Override
    public void deleteUserByPhone(String phone) {
        userDAO.deleteUserByPhone(phone);
    }

    @Override
    public void deleteUserByEmail(String email) {
        userDAO.deleteUserByEmail(email);
    }

    @Override
    public List<User> getAllUser() {
        return userDAO.getAllUser();
    }
}
