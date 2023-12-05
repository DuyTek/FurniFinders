package com.furnifinders.backend.service.impl;


import com.furnifinders.backend.Entity.Enum.PostStatus;
import com.furnifinders.backend.Entity.Enum.ProductStatus;
import com.furnifinders.backend.Entity.Enum.UserType;
import com.furnifinders.backend.Entity.Product;
import com.furnifinders.backend.Entity.ProductUserLink;
import com.furnifinders.backend.Entity.User;
import com.furnifinders.backend.Repository.ProductRepository;
import com.furnifinders.backend.Repository.ProductUserLinkRepository;
import com.furnifinders.backend.dto.PostProductRequest;
import com.furnifinders.backend.dto.RefreshTokenRequest;
import com.furnifinders.backend.service.EntityService.ProductEntityServiceImpl;
import com.furnifinders.backend.service.EntityService.UserEntityService;
import com.furnifinders.backend.service.JWTService;
import com.furnifinders.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final ProductRepository productRepository;
    private final ProductUserLinkRepository productUserLinkRepository;
    private final UserEntityService userEntityService;
    private final ProductEntityServiceImpl productEntityService;
    private final JWTService jwtService;

    @Override
    public UserDetailsService userDetailsService() {
        return username -> userEntityService.findUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

    @Override
    public Product findProductById(Long id) {
        return productEntityService.findProductById(id);
    }

    @Override
    public Product addProductImage(Long id, String image) {
        return productEntityService.addProductImage(id, image);
    }

    @Override
    public User findUserById(Long id) {
        return this.userEntityService.findUserById(id);
    }

    @Override
    public List<Product> findAllUserProducts(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
        User user = userEntityService.findUserByEmail(userEmail).orElseThrow();
        return productEntityService.findAllProductsByUserId(user.getUser_id());
    }

    @Override
    public List<Product> findAllProducts() {
        return productEntityService.findAllProducts();
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        return productEntityService.searchProducts(keyword);
    }

    @Override
    public Product updatePostStatus(Long id) {
        return productEntityService.updatePostStatus(id);
    }

    public Product addProduct(PostProductRequest postProductRequest) {
        Product product = new Product();

        product.setProduct_name(postProductRequest.getProduct_name());
        product.setProduct_description(postProductRequest.getProduct_description());
        product.setProduct_price(postProductRequest.getProduct_price());
        product.setProduct_percentage(postProductRequest.getProduct_percentage());
        product.setProduct_post_status(PostStatus.PENDING);
        product.setProduct_status(ProductStatus.AVAILABLE);
        product.setProduct_quantity(postProductRequest.getProduct_quantity());
        return productRepository.save(product);
    }

    @Override
    public ProductUserLink addProductUserLink(Product product, PostProductRequest postProductRequest) {
        ProductUserLink productUserLink = new ProductUserLink();
        productUserLink.setProduct(product);
        User user = userEntityService.findUserById(postProductRequest.getUser_id());
        productUserLink.setUser(user);
        productUserLink.setUserType(UserType.SELLER);
        return productUserLinkRepository.save(productUserLink);
    }
}