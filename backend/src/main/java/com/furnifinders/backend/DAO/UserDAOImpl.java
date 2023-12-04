package com.furnifinders.backend.DAO;

import com.furnifinders.backend.Entity.Enum.Role;
import com.furnifinders.backend.Entity.User;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class UserDAOImpl implements UserDAO{

    private final EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
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
}
