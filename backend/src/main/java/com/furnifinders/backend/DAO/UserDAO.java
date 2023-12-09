package com.furnifinders.backend.DAO;

import com.furnifinders.backend.Entity.Enum.Role;
import com.furnifinders.backend.Entity.User;

import java.util.Optional;

public interface UserDAO {
    Optional<User> findUserByPhone(String phone);
    Optional<User> findUserByEmail(String email);
    User findUserByRole(Role role);
    User findUserById(Long id);
}
