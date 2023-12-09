package com.furnifinders.backend.service.EntityService;

import com.furnifinders.backend.Entity.CartDetail;

public interface CartDetailEntityService {
    CartDetail findCartDetailByCartIdAndProductId(Long cart_id, Long product_id);

    void updateCartDetailQuantity(Long cartDetail_id, Long cartDetail_quantity);

    void updateCartDetailTotalPrice(Long cartDetail_id, Long cartDetail_total_price);
}
