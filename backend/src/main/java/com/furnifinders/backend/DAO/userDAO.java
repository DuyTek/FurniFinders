package com.furnifinders.backend.DAO;

import com.furnifinders.backend.Entity.User;

public interface userDAO {
    void addUser(User user);

    User getUser(int id);

    User getUserByEmail(String email);

    User getUserByPhone(String phone);

    void getAllUserByRole(String role);

    void updateUser(User user);

    void deleteUser(User user);

    void deleteUserByEmail(String email);
}
