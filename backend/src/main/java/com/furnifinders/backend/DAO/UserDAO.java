package com.furnifinders.backend.DAO;

import com.furnifinders.backend.Entity.User;

import java.util.List;

public interface UserDAO {
    void addUser(User user);

    User getUser(int id);

    User getUserByEmail(String email);

    User getUserByPhone(String phone);

    List<User> getAllUserByRole(String role);

    void updateUser(User user);

    void deleteUser(User user);

    void deleteUserByEmail(String email);

    List<User> getAllUser();
}
