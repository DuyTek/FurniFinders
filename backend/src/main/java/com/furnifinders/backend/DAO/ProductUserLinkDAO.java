package com.furnifinders.backend.DAO;

import com.furnifinders.backend.Entity.Enum.UserType;

import java.util.Optional;

public interface ProductUserLinkDAO {
    Optional<UserType> findUserTypeByProductIdAndUserId(Long product_id, Long user_id);
}
