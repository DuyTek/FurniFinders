package com.furnifinders.backend.DAO;

import com.furnifinders.backend.Entity.Enum.Role;
import com.furnifinders.backend.dto.Request.EditProfileRequest;
import com.furnifinders.backend.Entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    Optional<User> findUserByPhone(String phone);
    Optional<User> findUserByEmail(String email);
    List<User> findAllUsers();
    User findUserByRole(Role role);
    User findUserById(Long id);
    void editProfile(User user, EditProfileRequest editProfileRequest);
    void verifyUser(Long id);
}
