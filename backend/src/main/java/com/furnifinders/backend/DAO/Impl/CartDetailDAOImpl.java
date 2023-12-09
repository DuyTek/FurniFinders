package com.furnifinders.backend.DAO.Impl;

import com.furnifinders.backend.DAO.CartDetailDAO;
import com.furnifinders.backend.Entity.CartDetail;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class CartDetailDAOImpl implements CartDetailDAO {

    private final EntityManager entityManager;

    @Autowired
    public CartDetailDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public CartDetail findCartDetailByCartIdAndProductId(Long cart_id, Long product_id) {

        String query = "SELECT c FROM CartDetail c WHERE c.cart.cart_id = :cart_id AND c.product.product_id = :product_id";
        return this.entityManager.createQuery(query, CartDetail.class)
                .setParameter("cart_id", cart_id)
                .setParameter("product_id", product_id)
                .getSingleResult();
    }

    @Override
    public void updateCartDetailQuantity(Long cartDetail_id, Long cartDetail_quantity) {
        String query = "UPDATE CartDetail c SET c.cartDetail_quantity = :cartDetail_quantity WHERE c.cartDetail_id = :cartDetail_id";
        this.entityManager.createQuery(query)
                .setParameter("cartDetail_quantity", cartDetail_quantity)
                .setParameter("cartDetail_id", cartDetail_id)
                .executeUpdate();
    }

    @Override
    public void updateCartDetailTotalPrice(Long cartDetail_id, Long cartDetail_total_price) {
        String query = "UPDATE CartDetail c SET c.cartDetail_total_price = :cartDetail_total_price WHERE c.cartDetail_id = :cartDetail_id";
        this.entityManager.createQuery(query)
                .setParameter("cartDetail_total_price", cartDetail_total_price)
                .setParameter("cartDetail_id", cartDetail_id)
                .executeUpdate();
    }
}
