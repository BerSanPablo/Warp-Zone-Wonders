package com.review.tfg.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "REVIEW")
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Lob
	@Column(length = 5000)
	private String textoReview;
	
	@Column
	private int valoracion;
	
	@ManyToOne
	@JoinColumn(name = "id_escritor")
	private Usuario escritor;
	
	@ManyToOne
	@JoinColumn(name = "id_videojuego")
	private Videojuego videojuego;
	
	public Review() {
		
	}

	public Review(String textoReview, int valoracion, Usuario escritor, Videojuego videojuego) {
		this.textoReview = textoReview;
		this.valoracion = valoracion;
		this.escritor = escritor;
		this.videojuego = videojuego;

		this.id = null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTextoReview() {
		return textoReview;
	}

	public void setTextoReview(String textoReview) {
		this.textoReview = textoReview;
	}

	public int getValoracion() {
		return valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}

	public Usuario getEscritor() {
		return escritor;
	}

	public void setEscritor(Usuario escritor) {
		this.escritor = escritor;
	}

	public Videojuego getVideojuego() {
		return videojuego;
	}

	public void setVideojuego(Videojuego videojuego) {
		this.videojuego = videojuego;
	}
}
