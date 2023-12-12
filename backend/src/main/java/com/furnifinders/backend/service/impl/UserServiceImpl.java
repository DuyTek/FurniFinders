package com.furnifinders.backend.service.impl;


import com.furnifinders.backend.Entity.*;
import com.furnifinders.backend.Entity.Enum.*;
import com.furnifinders.backend.Repository.*;
import com.furnifinders.backend.dto.Request.AddToCartRequest;
import com.furnifinders.backend.dto.Request.PayRequest;
import com.furnifinders.backend.dto.Request.PostProductRequest;
import com.furnifinders.backend.dto.Response.AddToCartResponse;
import com.furnifinders.backend.service.EntityService.*;
import com.furnifinders.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    //Repository
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final ProductUserLinkRepository productUserLinkRepository;
    private final CartDetailRepository cartDetailRepository;
    private final OrderRepository orderRepository;

    //Service
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
        var userType = productUserLinkEntityService.findUserTypeByProductIdAndUserId(product_id, user_id).orElse(null);
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
                addToCartResponse.setMessage("Add to cart successfully");
            }
            else{
                CartDetail cart_detail = cartDetailEntityService.findCartDetailByCartIdAndProductId(cart.getCart_id(), product_id);
                if(cart_detail == null){
                    cart_detail = new CartDetail();
                    cart_detail.setCart(cart);
                    cart_detail.setProduct(product);
                    cart_detail.setCart_detail_quantity(product_quantity);
                    cart_detail.setCart_detail_total_price(product_quantity * product.getProduct_price());
                    cartDetailRepository.save(cart_detail);
                    addToCartResponse.setMessage("Add to cart successfully");
                }
                else{
                    if(product.getProduct_quantity() < cart_detail.getCart_detail_quantity() + product_quantity) {
                        addToCartResponse.setMessage("Product quantity is not enough");
                        return addToCartResponse;
                    }
                    cartDetailEntityService.updateCartDetailQuantity(cart_detail.getCart_detail_id(), cart_detail.getCart_detail_quantity() + product_quantity);
                    cartDetailEntityService.updateCartDetailTotalPrice(cart_detail.getCart_detail_id(), cart_detail.getCart_detail_total_price() + product_quantity * product.getProduct_price());
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
            productUserLinkRepository.save(productUserLink);

            Cart cart = new Cart();
            addCartToUser(product_quantity, user, product, cart);
            addToCartResponse.setMessage("Add to cart successfully");
        }

        return addToCartResponse;
    }

    @Override
    public List<Product> findAllApprovedProducts() {
        return productEntityService.findAllApprovedProducts();
    }

    @Override
    public void deleteUserProduct(Long id) {
        this.productEntityService.deleteUserProduct(id);
    }

    @Override
    public List<Product> getCurrentCart(Long user_id) {
        return cartDetailEntityService.getCurrentCart(user_id);
    }

    @Override
    public void pay(PayRequest payRequest) {
        Long user_id = payRequest.getUser_id();
        Cart cart = cartEntityService.findPendingCartByUserId(user_id);
        cart.setCart_status(CartStatus.PAID);
        cartEntityService.SetCartStatus(cart.getCart_id(), CartStatus.PAID);

        Long order_total_price = 0L;
        Long order_total_quantity = 0L;

        List<Product> products = cartDetailEntityService.getCurrentCart(user_id);

        for(Product product : products) {
        	order_total_price += product.getProduct_price();
        	order_total_quantity += product.getProduct_quantity();
        }

        Date order_created_date = new Date();
        String order_delivery_address = payRequest.getOrder_delivery_address();
        String order_delivery_phone = payRequest.getOrder_delivery_phone();
        String order_delivery_note = payRequest.getOrder_delivery_note();
        PaymentMethod order_payment_method = payRequest.getOrder_payment_method();
        DeliveryStatus order_delivery_status = DeliveryStatus.PENDING;
        PaymentStatus order_payment_status = PaymentStatus.PENDING;

        Order order = new Order();
        order.setOrder_created_date(order_created_date);
        order.setOrder_delivery_address(order_delivery_address);
        order.setOrder_delivery_phone(order_delivery_phone);
        order.setOrder_delivery_note(order_delivery_note);
        order.setOrder_payment_method(order_payment_method);
        order.setOrder_delivery_status(order_delivery_status);
        order.setOrder_payment_status(order_payment_status);
        order.setOrder_total_price(order_total_price);
        order.setOrder_total_quantity(order_total_quantity);
        order.setOrder_cart(cart);

        orderRepository.save(order);
    }

    private void addCartToUser(Long product_quantity, User user, Product product, Cart cart) {
        cart.setUser(user);
        cart.setCart_status(CartStatus.PENDING);
        cart = cartRepository.save(cart);

        CartDetail cart_detail = new CartDetail();
        cart_detail.setCart(cart);
        cart_detail.setProduct(product);
        cart_detail.setCart_detail_quantity(product_quantity);
        cart_detail.setCart_detail_total_price(product_quantity * product.getProduct_price());
        cartDetailRepository.save(cart_detail);
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