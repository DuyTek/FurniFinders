package com.furnifinders.backend.service;

import com.furnifinders.backend.Entity.Product;
import com.furnifinders.backend.Entity.ProductUserLink;
import com.furnifinders.backend.Entity.User;
import com.furnifinders.backend.dto.PostProductRequest;
import com.furnifinders.backend.dto.RefreshTokenRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {

    User findUserById(Long id);

    List<Product> findAllUserProducts(RefreshTokenRequest refreshTokenRequest);

    List<Product> findAllProducts();

    List<Product> searchProducts(String keyword);

    Product updatePostStatus(Long id);

    Product addProduct(PostProductRequest postProductRequest);
    ProductUserLink addProductUserLink(Product product, PostProductRequest postProductRequest);

    UserDetailsService userDetailsService();
}