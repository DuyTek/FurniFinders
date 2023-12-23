package com.furnifinders.backend.dto.Request;

import java.time.LocalDate;

public record EditProfileRequest(
    String user_first_name,

    String user_last_name,

    String user_email,

    String user_phone,
    String user_address,
    LocalDate user_dob
) {
};