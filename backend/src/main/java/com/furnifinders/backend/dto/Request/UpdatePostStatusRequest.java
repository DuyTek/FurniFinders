package com.furnifinders.backend.dto.Request;

import lombok.Data;

@Data
public class UpdatePostStatusRequest {
    private Long product_id;
    private String postStatus;
}
