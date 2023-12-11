package com.furnifinders.backend.DAO.Impl;

import com.furnifinders.backend.DAO.ProductUserLinkDAO;
import com.furnifinders.backend.Entity.Enum.UserType;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProductUserLinkDAOImpl implements ProductUserLinkDAO {

    private final EntityManager entityManager;

    @Autowired
    public ProductUserLinkDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<UserType> findUserTypeByProductIdAndUserId(Long product_id, Long user_id) {
        String query = "SELECT pul.userType FROM ProductUserLink pul WHERE pul.product.product_id = :product_id AND pul.user.user_id = :user_id";
        return entityManager.createQuery(query, UserType.class)
                .setParameter("product_id", product_id)
                .setParameter("user_id", user_id)
                .getResultList()
                .stream()
                .findFirst();
    }
}
