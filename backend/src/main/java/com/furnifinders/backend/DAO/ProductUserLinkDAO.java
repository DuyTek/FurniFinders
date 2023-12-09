package com.furnifinders.backend.DAO;

import com.furnifinders.backend.Entity.Enum.UserType;

public interface ProductUserLinkDAO {
    UserType findUserTypeByProductIdAndUserId(Long product_id, Long user_id);
}
