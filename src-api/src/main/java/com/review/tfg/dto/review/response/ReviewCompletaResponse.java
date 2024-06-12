package com.review.tfg.dto.review.response;

import com.review.tfg.entity.Review;

public class ReviewCompletaResponse extends ReviewResponse {
	
	private String nombreVideojuego;
	private byte[] imagenVideojuego;

	public ReviewCompletaResponse(Review review) {
		super(review);

		this.nombreVideojuego = review.getVideojuego().getNombre();
		this.imagenVideojuego = review.getVideojuego().getImagenPortada();
	}

	public String getNombreVideojuego() {
		return nombreVideojuego;
	}

	public void setNombreVideojuego(String nombreVideojuego) {
		this.nombreVideojuego = nombreVideojuego;
	}

	public byte[] getImagenVideojuego() {
		return imagenVideojuego;
	}

	public void setImagenVideojuego(byte[] imagenVideojuego) {
		this.imagenVideojuego = imagenVideojuego;
	}
}
