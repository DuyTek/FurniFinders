package com.furnifinders.backend.service;

import com.furnifinders.backend.Entity.User;

import java.util.List;

public interface UserService {
    User addUser(User user);

    User getUser(int id);

    User getUserByEmail(String email);

    User getUserByPhone(String phone);

    List<User> getAllUserByRole(String role);

    void updateUser(User user);

    void deleteUser(User user);

    void deleteUserByEmail(String email);

    List<User> getAllUser();
}
