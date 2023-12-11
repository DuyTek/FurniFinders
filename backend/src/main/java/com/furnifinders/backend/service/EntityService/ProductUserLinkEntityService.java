package com.furnifinders.backend.service.EntityService;

import com.furnifinders.backend.Entity.Enum.UserType;

import java.util.Optional;

public interface ProductUserLinkEntityService {
    Optional<UserType> findUserTypeByProductIdAndUserId(Long product_id, Long user_id);
}
