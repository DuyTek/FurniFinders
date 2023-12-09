package com.furnifinders.backend.service.impl;


import com.furnifinders.backend.Entity.*;
import com.furnifinders.backend.Entity.Enum.CartStatus;
import com.furnifinders.backend.Entity.Enum.PostStatus;
import com.furnifinders.backend.Entity.Enum.ProductStatus;
import com.furnifinders.backend.Entity.Enum.UserType;
import com.furnifinders.backend.Repository.CartDetailRepository;
import com.furnifinders.backend.Repository.CartRepository;
import com.furnifinders.backend.Repository.ProductRepository;
import com.furnifinders.backend.Repository.ProductUserLinkRepository;
import com.furnifinders.backend.dto.Request.AddToCartRequest;
import com.furnifinders.backend.dto.Request.PostProductRequest;
import com.furnifinders.backend.dto.Response.AddToCartResponse;
import com.furnifinders.backend.service.EntityService.*;
import com.furnifinders.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final ProductUserLinkRepository productUserLinkRepository;
    private final CartDetailRepository cartDetailRepository;


    private final UserEntityService userEntityService;
    private final ProductEntityService productEntityService;
    private final ProductUserLinkEntityService productUserLinkEntityService;
    private final CartEntityService cartEntityService;
    private final CartDetailEntityService cartDetailEntityService;

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
    public Product updateRejectPostStatus(Long id) {
        return productEntityService.updateRejectPostStatus(id);
    }

    @Override
    public AddToCartResponse addToCart(AddToCartRequest addToCartRequest) {
        Long product_id = addToCartRequest.getProduct_id();
        Long user_id = addToCartRequest.getUser_id();
        Long product_quantity = addToCartRequest.getProduct_quantity();
        UserType userType = productUserLinkEntityService.findUserTypeByProductIdAndUserId(product_id, user_id);
        User user = userEntityService.findUserById(user_id);
        Product product = productEntityService.findProductById(product_id);


        AddToCartResponse addToCartResponse = new AddToCartResponse();


        if(product.getProduct_quantity() < product_quantity) {
            addToCartResponse.setMessage("Product quantity is not enough");
            return addToCartResponse;
        }

        if(userType == UserType.BUYER) {
            Cart cart = cartEntityService.findPendingCartByUserId(user_id);
            if(cart == null){
                cart = new Cart();
                addCartToUser(product_quantity, user, product, cart);
            }
            else{
                CartDetail cartDetail = cartDetailEntityService.findCartDetailByCartIdAndProductId(cart.getCart_id(), product_id);
                if(cartDetail == null){
                    cartDetail = new CartDetail();
                    cartDetail.setCart(cart);
                    cartDetail.setProduct(product);
                    cartDetail.setCartDetail_quantity(product_quantity);
                    cartDetail.setCartDetail_total_price(product_quantity * product.getProduct_price());
                    cartDetailRepository.save(cartDetail);
                    addToCartResponse.setMessage("Add to cart successfully");
                }
                else{
                    if(product.getProduct_quantity() < cartDetail.getCartDetail_quantity() + product_quantity) {
                        addToCartResponse.setMessage("Product quantity is not enough");
                        return addToCartResponse;
                    }
                    cartDetailEntityService.updateCartDetailQuantity(cartDetail.getCartDetail_id(), cartDetail.getCartDetail_quantity() + product_quantity);
                    cartDetailEntityService.updateCartDetailTotalPrice(cartDetail.getCartDetail_id(), cartDetail.getCartDetail_total_price() + product_quantity * product.getProduct_price());
                }
            }
        }
        else if(userType == UserType.SELLER) {
            addToCartResponse.setMessage("You are a seller, you cannot add to cart");
        }
        else{
            ProductUserLink productUserLink = new ProductUserLink();
            productUserLink.setUser(user);
            productUserLink.setProduct(product);
            productUserLink.setUserType(UserType.BUYER);

            Cart cart = new Cart();
            addCartToUser(product_quantity, user, product, cart);
            addToCartResponse.setMessage("Add to cart successfully");
        }

        return addToCartResponse;
    }

    private void addCartToUser(Long product_quantity, User user, Product product, Cart cart) {
        cart.setUser(user);
        cart.setCart_status(CartStatus.PENDING);
        cart = cartRepository.save(cart);

        CartDetail cartDetail = new CartDetail();
        cartDetail.setCart(cart);
        cartDetail.setProduct(product);
        cartDetail.setCartDetail_quantity(product_quantity);
        cartDetail.setCartDetail_total_price(product_quantity * product.getProduct_price());
        cartDetailRepository.save(cartDetail);
    }

    @Override
    public User findUserById(Long id) {
        return this.userEntityService.findUserById(id);
    }

    @Override
    public List<Product> findAllUserProducts(Long user_id) {
        User user = userEntityService.findUserById(user_id);
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
    public Product updateApprovePostStatus(Long id) {
        return productEntityService.updateApprovePostStatus(id);
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
    public void addProductUserLink(Product product, Long user_id) {
        ProductUserLink productUserLink = new ProductUserLink();
        productUserLink.setProduct(product);

        User user = userEntityService.findUserById(user_id);

        productUserLink.setUser(user);
        productUserLink.setUserType(UserType.SELLER);
        productUserLinkRepository.save(productUserLink);
    }
}