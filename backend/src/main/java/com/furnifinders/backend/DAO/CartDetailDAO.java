package com.furnifinders.backend.DAO;

import com.furnifinders.backend.Entity.CartDetail;
import com.furnifinders.backend.Entity.Product;

import java.util.List;

public interface CartDetailDAO {
    CartDetail findCartDetailByCartIdAndProductId(Long cart_id, Long product_id);

    void updateCartDetailQuantity(Long cart_detail_id, Long cart_detail_quantity);

    void updateCartDetailTotalPrice(Long cart_detail_id, Long cart_detail_total_price);

    List<Product> getCurrentCart(Long user_id);
}
