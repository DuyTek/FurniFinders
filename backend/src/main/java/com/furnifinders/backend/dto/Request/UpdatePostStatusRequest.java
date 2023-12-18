package com.furnifinders.backend.dto.Request;

import com.furnifinders.backend.Entity.Enum.PostStatus;
import lombok.Data;

@Data
public class UpdatePostStatusRequest {
    private Long product_id;
    private PostStatus postStatus;
}
