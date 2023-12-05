package com.furnifinders.backend.dto.Response;

import com.furnifinders.backend.Entity.Enum.Role;
import lombok.Data;

@Data
public class SignInResponse {
        private Long user_id;
        private String user_first_name;
        private String user_last_name;
        private String user_email;
        private String user_phone;
        private Role user_role;
        private String token;
        private String refreshToken;
}
