package com.furnifinders.backend.DAO;

import com.furnifinders.backend.Entity.Cart;

public interface CartDAO {
    Cart findPendingCartByUserId(Long user_id);
}
