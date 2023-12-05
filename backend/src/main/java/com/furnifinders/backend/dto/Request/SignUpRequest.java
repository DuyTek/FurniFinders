package com.furnifinders.backend.dto.Request;

import lombok.Data;

@Data
public class SignUpRequest {

    private String user_first_name;
    private String user_last_name;
    private String user_email;
    private String user_phone;
    private String user_password;
}