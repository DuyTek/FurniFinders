package com.furnifinders.backend.service.EntityService;

import com.furnifinders.backend.Entity.Product;

import java.util.List;

public interface ProductEntityService {
    List<Product> findAllProductsByUserId(Long id);
    List<Product> findAllProducts();

    Product updatePostStatus(Long id);

    List<Product> searchProducts(String keyword);
}
