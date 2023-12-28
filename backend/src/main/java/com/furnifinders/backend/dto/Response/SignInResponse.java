package com.furnifinders.backend.dto.Response;

import java.time.LocalDate;

import com.furnifinders.backend.Entity.Enum.Gender;
import com.furnifinders.backend.Entity.Enum.Role;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class SignInResponse {
        private Long user_id;
        private String user_first_name;
        private String user_last_name;
        private String user_email;
        private String user_phone;

        @Enumerated(EnumType.STRING)
        private Role user_role;

        private String token;
        private String refreshToken;
        private String user_address;
        private LocalDate user_dob;
        private String user_verified;

        @Enumerated(EnumType.STRING)
        private Gender user_gender;
}
