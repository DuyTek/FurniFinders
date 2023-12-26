package com.furnifinders.backend.service.EntityService.Impl;

import com.furnifinders.backend.DAO.UserDAO;
import com.furnifinders.backend.Entity.Enum.Role;
import com.furnifinders.backend.dto.Request.EditProfileRequest;
import com.furnifinders.backend.Entity.User;
import com.furnifinders.backend.service.EntityService.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserEntityServiceImpl implements UserEntityService {

    private final UserDAO userDAO;

    @Autowired
    public UserEntityServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return this.userDAO.findUserByEmail(email);
    }

    @Override
    public User findUserByRole(Role role) {
        return this.userDAO.findUserByRole(role);
    }

    @Override
    public User findUserById(Long id) {
        return this.userDAO.findUserById(id);
    }

    @Override
    public Optional<User> findUserByPhone(String phone) {
        return this.userDAO.findUserByPhone(phone);
    }

    @Override
    public void editProfile(User user, EditProfileRequest editProfileRequest) {
        this.userDAO.editProfile(user, editProfileRequest);
    }

    @Override
    public void verifyUser(Long id) {
        this.userDAO.verifyUser(id);
    }
}
