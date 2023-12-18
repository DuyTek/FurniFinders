package com.furnifinders.backend.service.EntityService;

import com.furnifinders.backend.Entity.Product;

import java.util.List;

public interface ProductEntityService {
    List<Product> findAllProductsByUserId(Long id);
    List<Product> findAllProducts();
    Product updateApprovePostStatus(Long id);
    Product updateRejectPostStatus(Long id);
    List<Product> searchProducts(String keyword);
    Product findProductById(Long id);
    Product addProductImage(Long id, String image);
    List<Product> findAllApprovedProducts();

    void deleteUserProduct(Long id);
}
