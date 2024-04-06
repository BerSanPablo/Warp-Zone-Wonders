package com.review.tfg.dto.usuario.response;

import java.util.Date;

import com.review.tfg.entity.Usuario;

public class UsuarioDetallesResponse {
	
	private byte[] imagenPerfil;
	private String nick;
	private String email;
	private String comunidad;
	private Date fechaCreacion;
	
	public UsuarioDetallesResponse() {}
	
	
	public UsuarioDetallesResponse(Usuario usuario) {
		this.imagenPerfil = usuario.getImagenPerfil();
		this.nick = usuario.getNick();
		this.email = usuario.getEmail();
		this.comunidad = usuario.getComunidad();
		this.fechaCreacion = usuario.getFechaCreacion();
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


	public Date getFechaCreacion() {
		return fechaCreacion;
	}


	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
}
