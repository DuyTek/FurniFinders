package com.furnifinders.backend.service.EntityService.Impl;


import com.furnifinders.backend.DAO.CartDetailDAO;
import com.furnifinders.backend.Entity.CartDetail;
import com.furnifinders.backend.Entity.Product;
import com.furnifinders.backend.service.EntityService.CartDetailEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartDetailEntityServiceImpl implements CartDetailEntityService {
    private final CartDetailDAO cart_detailDAO;

    @Autowired
    public CartDetailEntityServiceImpl(CartDetailDAO cart_detailDAO) {
        this.cart_detailDAO = cart_detailDAO;
    }


    @Override
    public CartDetail findCartDetailByCartIdAndProductId(Long cart_id, Long product_id) {
        return this.cart_detailDAO.findCartDetailByCartIdAndProductId(cart_id, product_id);
    }

    @Override
    public void updateCartDetailQuantity(Long cart_detail_id, Long cart_detail_quantity) {
        this.cart_detailDAO.updateCartDetailQuantity(cart_detail_id, cart_detail_quantity);
    }

    @Override
    public void updateCartDetailTotalPrice(Long cart_detail_id, Long cart_detail_total_price) {
        this.cart_detailDAO.updateCartDetailTotalPrice(cart_detail_id, cart_detail_total_price);
    }

    @Override
    public List<Product> getCurrentCart(Long user_id) {
        return this.cart_detailDAO.getCurrentCart(user_id);
    }


}
