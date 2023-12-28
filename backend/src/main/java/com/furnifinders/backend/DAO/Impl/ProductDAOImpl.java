package com.furnifinders.backend.DAO.Impl;

import com.furnifinders.backend.DAO.ProductDAO;
import com.furnifinders.backend.Entity.Enum.PostStatus;
import com.furnifinders.backend.Entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {
    private final EntityManager entityManager;

    @Autowired
    public ProductDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Product> findAllProductsByUserId(Long id) {
        String query = "SELECT p FROM Product p inner join p.productUserLinkList i where i.user.user_id = :id";
        return this.entityManager.createQuery(query, Product.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public List<Product> findAllProducts() {
        return this.entityManager.createQuery("SELECT p FROM Product p", Product.class)
                .getResultList();
    }

    @Override
    public Product updateApprovePostStatus(Long id) {
        String query = "UPDATE Product p SET p.product_post_status = :status WHERE p.product_id = :id";
        this.entityManager.createQuery(query)
                .setParameter("status", PostStatus.APPROVED.toString())
                .setParameter("id", id)
                .executeUpdate();
        return this.entityManager.find(Product.class, id);
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        String query = "SELECT p FROM Product p WHERE p.product_name LIKE :keyword OR p.product_description LIKE :keyword";
        return this.entityManager.createQuery(query, Product.class)
                .setParameter("keyword", "%" + keyword + "%")
                .getResultList();
    }

    @Override
    public Product findProductById(Long id) {
        String query = "SELECT p FROM Product p WHERE p.product_id = :id";
        return this.entityManager.createQuery(query, Product.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    @Transactional
    public Product addProductImage(Long id, String image) {
        String query = "UPDATE Product p SET p.product_image = :image WHERE p.product_id = :id";
        this.entityManager.createQuery(query)
                .setParameter("image", image)
                .setParameter("id", id)
                .executeUpdate();
        return this.entityManager.find(Product.class, id);
    }

    @Override
    public Product updateRejectPostStatus(Long id) {
        String query = "UPDATE Product p SET p.product_post_status = :status WHERE p.product_id = :id";
        return this.entityManager.createQuery(query, Product.class)
                .setParameter("status", PostStatus.REJECTED)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<Product> findAllApprovedProducts() {
        String query = "SELECT p FROM Product p WHERE p.product_post_status = :status";
        return this.entityManager.createQuery(query, Product.class)
                .setParameter("status", PostStatus.APPROVED.toString())
                .getResultList();
    }

    @Override
    public void deleteUserProduct(Long id) {
        String query = "DELETE FROM Product p WHERE p.product_id = :id";
        this.entityManager.createQuery(query)
                .setParameter("id", id)
                .executeUpdate();
    }


}
