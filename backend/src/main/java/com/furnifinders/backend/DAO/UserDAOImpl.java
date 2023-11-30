package com.furnifinders.backend.DAO;

import com.furnifinders.backend.Entity.User;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private final EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        this.entityManager.persist(user);
    }

    @Override
    public User getUserByID(int id) {
        return this.entityManager.find(User.class, id);
    }

    @Override
    public User getUserByEmail(String email) {
        return this.entityManager.find(User.class, email);
    }

    @Override
    public User getUserByPhone(String phone) {
        return this.entityManager.find(User.class, phone);
    }

    @Override
    public List<User> getAllUserByRole(String role) {
        String sql = "SELECT user FROM User user WHERE user.role = :role";
        return this.entityManager.createQuery(sql, User.class).setParameter("role", role).getResultList();
    }

    @Override
    public List<User> getAllUser(){
        String sql = "SELECT user FROM User user";
        return this.entityManager.createQuery(sql, User.class).getResultList();
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        this.entityManager.merge(user);
    }

    @Override
    @Transactional
    public void deleteUserByPhone(String phone) {
        this.entityManager.remove(phone);
    }

    @Override
    @Transactional
    public void deleteUserByEmail(String email) {
        this.entityManager.remove(email);
    }

}
