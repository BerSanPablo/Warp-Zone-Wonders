package com.review.tfg.dto.auth.request;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

	@NotBlank(message = "El email no se puede dejar en blanco")
	private String email;
	@NotBlank(message = "La contraseña no se puede dejar en blanco")
	private String password;
	
	public LoginRequest() {}

	public LoginRequest(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
