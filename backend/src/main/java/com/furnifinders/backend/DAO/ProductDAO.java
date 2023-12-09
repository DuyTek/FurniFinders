package com.furnifinders.backend.DAO;

import com.furnifinders.backend.Entity.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> findAllProductsByUserId(Long id);
    List<Product> findAllProducts();

    Product updateApprovePostStatus(Long id);

    List<Product> searchProducts(String keyword);

    Product findProductById(Long id);

    Product addProductImage(Long id, String image);

    Product updateRejectPostStatus(Long id);
}
