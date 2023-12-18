package com.furnifinders.backend.dto.Request;

import lombok.Data;

@Data
public class AddToCartRequest {
    private Long product_id;
    private Long user_id;
    private Long product_quantity;
}
