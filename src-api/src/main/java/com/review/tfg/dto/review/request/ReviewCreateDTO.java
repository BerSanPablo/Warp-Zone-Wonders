package com.review.tfg.dto.review.request;

public class ReviewCreateDTO {
	private String nombreUsuario;
	private String nombreVideojuego;
	private int valoracion;
	private String textoReview;
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getNombreVideojuego() {
		return nombreVideojuego;
	}
	public void setNombreVideojuego(String nombreVideojuego) {
		this.nombreVideojuego = nombreVideojuego;
	}
	public int getValoracion() {
		return valoracion;
	}
	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}
	public String getTextoReview() {
		return textoReview;
	}
	public void setTextoReview(String textoReview) {
		this.textoReview = textoReview;
	}
}
