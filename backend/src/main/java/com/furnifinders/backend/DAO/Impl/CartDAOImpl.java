package com.furnifinders.backend.DAO.Impl;


import com.furnifinders.backend.DAO.CartDAO;
import com.furnifinders.backend.Entity.Cart;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CartDAOImpl implements CartDAO {
    private final EntityManager entityManager;

    @Autowired
    public CartDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Cart findPendingCartByUserId(Long user_id) {
        String query = "SELECT c FROM Cart c inner join c.user u where u.user_id = :id and c.cart_status = 'PENDING'";
        return this.entityManager.createQuery(query, Cart.class)
                .setParameter("id", user_id)
                .getSingleResult();
    }
}
