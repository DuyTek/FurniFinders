package com.furnifinders.backend.dto.Request;

import lombok.Data;

@Data
public class SignInRequest {

	private String user_email;
	private String user_password;
}