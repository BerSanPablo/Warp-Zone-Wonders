package com.review.tfg.dto.auth.response;

import java.util.Set;

import com.review.tfg.entity.Role;
import com.review.tfg.entity.Usuario;

public class LoginDataResponse {
	
	private String nick;
	private String comunidad;
	private byte[] imagenPerfil;
    private Set<Role> roles;
	
	public LoginDataResponse(Usuario usuario, Set<Role> roles) {
		this.nick = usuario.getNick();
		this.comunidad = usuario.getComunidad();
		this.imagenPerfil = usuario.getImagenPerfil();
		this.roles = roles;
	}
	
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getComunidad() {
		return comunidad;
	}
	public void setComunidad(String comunidad) {
		this.comunidad = comunidad;
	}
	public byte[] getImagenPerfil() {
		return imagenPerfil;
	}
	public void setImagenPerfil(byte[] imagenPerfil) {
		this.imagenPerfil = imagenPerfil;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
