package com.furnifinders.backend.DAO;

import com.furnifinders.backend.Entity.User;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class userDAOImpl implements userDAO{

    private final EntityManager entityManager;

    @Autowired
    public userDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addUser(User user) {
        this.entityManager.persist(user);
    }

    @Override
    public User getUser(int id) {
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
    public void getAllUserByRole(String role) {
        this.entityManager.find(User.class, role);
    }

    @Override
    public void updateUser(User user) {
        this.entityManager.merge(user);
    }

    @Override
    public void deleteUser(User user) {
        this.entityManager.remove(user);
    }

    @Override
    public void deleteUserByEmail(String email) {
        this.entityManager.remove(email);
    }

}
