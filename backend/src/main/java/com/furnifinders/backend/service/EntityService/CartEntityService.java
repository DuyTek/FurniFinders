package com.furnifinders.backend.service.EntityService;

import com.furnifinders.backend.Entity.Cart;

public interface CartEntityService {
    Cart findPendingCartByUserId(Long id);
}
