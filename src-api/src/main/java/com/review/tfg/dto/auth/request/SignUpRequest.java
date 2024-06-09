package com.review.tfg.dto.auth.request;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SignUpRequest {
	
	@Size(max = 16000000, message = "La imagen de perfil es demasiado grande")
	private MultipartFile imagenPerfil;
	
	@NotBlank(message = "El nick no se puede dejar vacío")
	@Size(max = 100, message = "El nick debe tener un maximo de 100 caracteres")
	private String nick;

	@NotBlank(message = "La contraseña no se puede dejar en blanco")
	@Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
	private String password;
	
	@NotBlank(message = "El email no se puede dejar vacío")
	private String email;
	
	private String comunidad;

	public SignUpRequest() {}

	public SignUpRequest(String password, MultipartFile imagenPerfil, String nick, String email, String comunidad) {
		this.password = password;
		this.comunidad = comunidad;
		this.email = email;
		this.imagenPerfil = imagenPerfil;
		this.nick = nick;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComunidad() {
		return comunidad;
	}

	public void setComunidad(String comunidad) {
		this.comunidad = comunidad;
	}

	public MultipartFile getImagenPerfil() {
		return imagenPerfil;
	}

	public void setImagenPerfil(MultipartFile imagenPerfil) {
		this.imagenPerfil = imagenPerfil;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}
}
