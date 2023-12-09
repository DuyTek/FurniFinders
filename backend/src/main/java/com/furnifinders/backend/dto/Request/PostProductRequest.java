package com.furnifinders.backend.dto.Request;

import lombok.Data;

@Data
public class PostProductRequest {
    private String product_name;
    private String product_description;
    private Long product_price;
    private Long product_percentage;
    private Long product_quantity;
}
