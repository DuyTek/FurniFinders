package com.furnifinders.backend.service.EntityService;

import com.furnifinders.backend.Entity.Cart;
import com.furnifinders.backend.Entity.Enum.CartStatus;

public interface CartEntityService {
    Cart findPendingCartByUserId(Long id);

    void SetCartStatus(Long cart_id, CartStatus cart_status);
}
