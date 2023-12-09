package com.furnifinders.backend.service.EntityService.Impl;


import com.furnifinders.backend.DAO.CartDetailDAO;
import com.furnifinders.backend.Entity.CartDetail;
import com.furnifinders.backend.service.EntityService.CartDetailEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartDetailEntityServiceImpl implements CartDetailEntityService {
    private final CartDetailDAO cartDetailDAO;

    @Autowired
    public CartDetailEntityServiceImpl(CartDetailDAO cartDetailDAO) {
        this.cartDetailDAO = cartDetailDAO;
    }


    @Override
    public CartDetail findCartDetailByCartIdAndProductId(Long cart_id, Long product_id) {
        return this.cartDetailDAO.findCartDetailByCartIdAndProductId(cart_id, product_id);
    }

    @Override
    public void updateCartDetailQuantity(Long cartDetail_id, Long cartDetail_quantity) {
        this.cartDetailDAO.updateCartDetailQuantity(cartDetail_id, cartDetail_quantity);
    }

    @Override
    public void updateCartDetailTotalPrice(Long cartDetail_id, Long cartDetail_total_price) {
        this.cartDetailDAO.updateCartDetailTotalPrice(cartDetail_id, cartDetail_total_price);
    }


}
