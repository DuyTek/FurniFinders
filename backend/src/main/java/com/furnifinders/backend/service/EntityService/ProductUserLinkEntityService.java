package com.furnifinders.backend.service.EntityService;

import com.furnifinders.backend.Entity.Enum.UserType;

public interface ProductUserLinkEntityService {
    UserType findUserTypeByProductIdAndUserId(Long product_id, Long user_id);
}
