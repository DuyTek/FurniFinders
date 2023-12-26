package com.furnifinders.backend.dto.Request;

import java.time.LocalDate;

import com.furnifinders.backend.Entity.Enum.Gender;

import jakarta.persistence.*;

public record EditProfileRequest(
    String user_first_name,

    String user_last_name,

    String user_email,

    String user_phone,
    String user_address,
    LocalDate user_dob,

    @Enumerated(EnumType.STRING)
    Gender user_gender
) {
};