package com.furnifinders.backend.service;

import com.furnifinders.backend.Entity.Product;
import com.furnifinders.backend.Entity.User;
import com.furnifinders.backend.dto.Request.AddToCartRequest;
import com.furnifinders.backend.dto.Request.EditProfileRequest;
import com.furnifinders.backend.dto.Request.PayRequest;
import com.furnifinders.backend.dto.Request.PostProductRequest;
import com.furnifinders.backend.dto.Response.AddToCartResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {

    User findUserById(Long id);

    List<Product> findAllUserProducts(Long user_id);

    List<Product> findAllProducts();

    List<Product> searchProducts(String keyword);

    Product updateApprovePostStatus(Long id);

    Product addProduct(PostProductRequest postProductRequest);
    void addProductUserLink(Product product, Long user_id);

    UserDetailsService userDetailsService();

    Product findProductById(Long id);

    Product addProductImage(Long id, String image);

    Product updateRejectPostStatus(Long id);

    AddToCartResponse addToCart(AddToCartRequest addToCartRequest);

    List<Product> findAllApprovedProducts();

    void deleteUserProduct(Long id);

    List<Product> getCurrentCart(Long user_id);

    void pay(PayRequest payRequest);
    void editUser(Long id, EditProfileRequest user);

    List<User> findAllUsers();

    void verifyUser(Long id);

    Product buy(Long id);
}