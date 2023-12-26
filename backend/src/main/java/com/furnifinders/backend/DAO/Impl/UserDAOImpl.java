package com.furnifinders.backend.DAO.Impl;

import com.furnifinders.backend.DAO.UserDAO;
import com.furnifinders.backend.Entity.Enum.Role;
import com.furnifinders.backend.Entity.Enum.UserVerify;
import com.furnifinders.backend.dto.Request.EditProfileRequest;
import com.furnifinders.backend.Entity.User;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class UserDAOImpl implements UserDAO {

    private final EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<User> findUserByPhone(String phone) {
        String query = "SELECT u FROM User u WHERE u.user_phone = :phone";
        return this.entityManager.createQuery(query, User.class)
                .setParameter("phone", phone)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return this.entityManager.createQuery("SELECT u FROM User u WHERE u.user_email = :email", User.class)
                .setParameter("email", email)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public User findUserByRole(Role role) {
        return this.entityManager.createQuery("SELECT u FROM User u WHERE u.user_role = :role", User.class)
                .setParameter("role", role)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public User findUserById(Long id) {
        return this.entityManager.createQuery("SELECT u FROM User u WHERE u.user_id = :id", User.class)
                .setParameter("id", id)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public void editProfile(User user, EditProfileRequest editProfileRequest) {
        this.entityManager.merge(user);
    }

    @Transactional
    @Override
    public void verifyUser(Long id) {
        String sql = "SELECT u FROM User u WHERE u.user_id = :id";
        User user = this.entityManager.createQuery(sql, User.class)
                .setParameter("id", id)
                .getSingleResult();
        if(user.getUser_verified()== UserVerify.NO){
            user.setUser_verified(UserVerify.YES);
        }
        else{
            user.setUser_verified(UserVerify.NO);
        }
        this.entityManager.merge(user);
    }
}
