package com.furnifinders.backend.DAO.Impl;

import com.furnifinders.backend.DAO.CartDetailDAO;
import com.furnifinders.backend.Entity.CartDetail;
import com.furnifinders.backend.Entity.Product;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


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
    public void updateCartDetailQuantity(Long cart_detail_id, Long cart_detail_quantity) {
        String query = "UPDATE CartDetail c SET c.cart_detail_quantity = :cart_detail_quantity WHERE c.cart_detail_id = :cart_detail_id";
        this.entityManager.createQuery(query)
                .setParameter("cart_detail_quantity", cart_detail_quantity)
                .setParameter("cart_detail_id", cart_detail_id)
                .executeUpdate();
    }

    @Override
    public void updateCartDetailTotalPrice(Long cart_detail_id, Long cart_detail_total_price) {
        String query = "UPDATE CartDetail c SET c.cart_detail_total_price = :cart_detail_total_price WHERE c.cart_detail_id = :cart_detail_id";
        this.entityManager.createQuery(query)
                .setParameter("cart_detail_total_price", cart_detail_total_price)
                .setParameter("cart_detail_id", cart_detail_id)
                .executeUpdate();
    }

    @Override
    public List<Product> getCurrentCart(Long user_id) {
        String query = "SELECT p FROM Product p inner join p.cart_detailList c inner join c.cart ca inner join ca.user u where u.user_id = :id and ca.cart_status = 'PENDING'";
        return this.entityManager.createQuery(query, Product.class)
                .setParameter("id", user_id)
                .getResultList();
    }
}
