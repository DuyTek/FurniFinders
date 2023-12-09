package com.furnifinders.backend.DAO.Impl;

import com.furnifinders.backend.DAO.ProductUserLinkDAO;
import com.furnifinders.backend.Entity.Enum.UserType;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductUserLinkDAOImpl implements ProductUserLinkDAO {

    private final EntityManager entityManager;

    @Autowired
    public ProductUserLinkDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public UserType findUserTypeByProductIdAndUserId(Long product_id, Long user_id) {
        String query = "SELECT p.userType FROM ProductUserLink p WHERE p.product.product_id = :product_id AND p.user.user_id = :user_id";
        return this.entityManager.createQuery(query, UserType.class)
                .setParameter("product_id", product_id)
                .setParameter("user_id", user_id)
                .getSingleResult();
    }
}
