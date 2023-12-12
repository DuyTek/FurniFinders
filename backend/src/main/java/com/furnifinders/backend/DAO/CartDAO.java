package com.furnifinders.backend.DAO;

import com.furnifinders.backend.Entity.Cart;
import com.furnifinders.backend.Entity.Enum.CartStatus;

public interface CartDAO {
    Cart findPendingCartByUserId(Long user_id);

    void SetCartStatus(Long cart_id, CartStatus cart_status);
}
