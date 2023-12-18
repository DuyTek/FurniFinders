package com.furnifinders.backend.service.EntityService.Impl;

import com.furnifinders.backend.DAO.CartDAO;
import com.furnifinders.backend.Entity.Cart;
import com.furnifinders.backend.Entity.Enum.CartStatus;
import com.furnifinders.backend.service.EntityService.CartEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartEntityServiceImpl implements CartEntityService {
    private final CartDAO cartDAO;

    @Autowired
    public CartEntityServiceImpl(CartDAO cartDAO) {
        this.cartDAO = cartDAO;
    }


    @Override
    public Cart findPendingCartByUserId(Long id) {
        return cartDAO.findPendingCartByUserId(id);
    }

    @Override
    public void SetCartStatus(Long cart_id, CartStatus cart_status) {
        cartDAO.SetCartStatus(cart_id, cart_status);
    }
}
