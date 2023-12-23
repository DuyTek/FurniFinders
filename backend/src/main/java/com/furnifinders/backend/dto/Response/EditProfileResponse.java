package com.furnifinders.backend.dto.Response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EditProfileResponse {
    private String message;

    public EditProfileResponse(String message) {
        this.message = message;
    }
}
