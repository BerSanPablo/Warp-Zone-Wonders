package com.review.tfg.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "TAG")
public class Tag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String nombre;
	
	@Lob
	@Column(length = 5000)
	private String descripción;

	@ManyToMany(mappedBy = "tags")
	private List<Videojuego> listaVideojuegos;
	
	public Tag() {
		
	}
	
	public Tag(String nombre, String descripción) {
		super();
		this.nombre = nombre;
		this.descripción = descripción;
		
		this.id = null;
		this.listaVideojuegos = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripción() {
		return descripción;
	}

	public void setDescripción(String descripción) {
		this.descripción = descripción;
	}

	public List<Videojuego> getListaVideojuegos() {
		return listaVideojuegos;
	}

	public void setListaVideojuegos(List<Videojuego> listaVideojuegos) {
		this.listaVideojuegos = listaVideojuegos;
	}
}
