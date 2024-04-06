package com.review.tfg.dto.usuario.response;

import com.review.tfg.entity.Usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioSimplificadoResponse {
	
	@Size(max = 16000000, message = "La imagen de perfil es demasiado grande")
	private byte[] imagenPerfil;
	
	@NotBlank(message = "El nick no se puede dejar vacío")
	@Size(max = 100, message = "El nick debe tener un maximo de 100 caracteres")
	private String nick;
	
	public UsuarioSimplificadoResponse() {}
	
	public UsuarioSimplificadoResponse(Usuario usuario) {
		this.imagenPerfil = usuario.getImagenPerfil();
		this.nick = usuario.getNick();
	}

	public UsuarioSimplificadoResponse(byte[] imagenPerfil, String nick) {
		this.imagenPerfil = imagenPerfil;
		this.nick = nick;
	}

	public byte[] getImagenPerfil() {
		return imagenPerfil;
	}

	public void setImagenPerfil(byte[] imagenPerfil) {
		this.imagenPerfil = imagenPerfil;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}
}
