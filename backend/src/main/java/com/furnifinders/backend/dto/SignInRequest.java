package com.furnifinders.backend.dto;

import lombok.Data;

@Data
public class SignInRequest {

	private String user_email;
	private String user_password;
}