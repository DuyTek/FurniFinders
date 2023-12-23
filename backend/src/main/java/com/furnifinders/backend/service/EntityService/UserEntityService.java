package com.furnifinders.backend.service.EntityService;

import com.furnifinders.backend.Entity.Enum.Role;
import com.furnifinders.backend.dto.Request.EditProfileRequest;
import com.furnifinders.backend.Entity.User;

import java.util.Optional;

public interface UserEntityService {
    Optional<User> findUserByEmail(String email);
    User findUserByRole(Role role);
    User findUserById(Long id);
    Optional<User> findUserByPhone(String phone);
    void editProfile(User user, EditProfileRequest editProfileRequest);
}
