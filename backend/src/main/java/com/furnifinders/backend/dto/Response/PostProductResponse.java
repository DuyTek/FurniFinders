package com.furnifinders.backend.dto.Response;

import com.furnifinders.backend.Entity.Enum.PostStatus;
import com.furnifinders.backend.Entity.Enum.ProductStatus;
import lombok.Data;

@Data
public class PostProductResponse {
    private Long product_id;
    private String product_name;
    private String product_description;
    private String product_image;
    private Long product_percentage;
    private Long product_price;
    private ProductStatus product_status;
    private PostStatus product_post_status;
    private Long product_quantity;
}

