package com.review.tfg.dto.review.response;

import com.review.tfg.entity.Review;

public class ReviewResponse {
	private String nombreUsuario;
	private byte[] imagenUsuario;
	private int valoracion;
	private String comentario;
	
	public ReviewResponse(Review review) {
		this.nombreUsuario = review.getEscritor().getNick();
		this.imagenUsuario = review.getEscritor().getImagenPerfil();
		this.valoracion = review.getValoracion();
		this.comentario = review.getTextoReview();
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public byte[] getImagenUsuario() {
		return imagenUsuario;
	}
	public void setImagenUsuario(byte[] imagenUsuario) {
		this.imagenUsuario = imagenUsuario;
	}
	public int getValoracion() {
		return valoracion;
	}
	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
}
