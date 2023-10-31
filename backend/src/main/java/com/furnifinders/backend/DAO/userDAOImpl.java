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
}
