package com.furnifinders.backend.dto.Request;

import lombok.Data;

@Data
public class PostProductRequest {
    private Long user_id;
    private String product_name;
    private String product_description;
    private int product_price;
    private int product_percentage;
    private int product_quantity;
}
