package com.review.tfg.entity;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.review.tfg.dto.videojuego.request.VideojuegoCreateDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "VIDEOJUEGO")
public class Videojuego {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique=true)
	@NotBlank(message = "El nombre no puede ser nulo")
	private String nombre;
	
	@Column
	@NotNull(message = "La fecha de creacion no puede ser nula")
	@Temporal(TemporalType.DATE)
	private Date fechaCreacion;
	
	@Lob
	@Column(length = 16000000)
	@Size(max = 16000000, message = "La imagen de portada es demasiado grande")
	private byte[] imagenPortada;
	
	@Lob
	@Column(length = 5000)
	@NotBlank(message = "La sinopsis no puede ser nula")
	@Size(max = 5000, message = "La sinópsis es demasiado larga, se permiten hasta 5000 caracteres")
	private String sinopsis;
	
	@OneToMany(mappedBy = "videojuego")
	private Set<Review> listaReviews;
	
	@ManyToMany
	@JoinTable(name = "VIDEOJUEGO_TAG",
				joinColumns = @JoinColumn(name = "id_vieojuego"),
				inverseJoinColumns = @JoinColumn(name = "id_tag"))
	private Set<Tag> tags;
	
	public Videojuego() {
		
	}

	public Videojuego(String nombre, Date fechaCreacion, byte[] imagenPortada, String sinopsis) {
		this.nombre = nombre;
		this.fechaCreacion = fechaCreacion;
		this.imagenPortada = imagenPortada;
		this.sinopsis = sinopsis;
		
		
		this.id = null;
		this.listaReviews = new HashSet<>();
		this.tags = new HashSet<>();
	}

	public Videojuego(VideojuegoCreateDTO videojuego){
		this.nombre = videojuego.getNombre();
		this.fechaCreacion = videojuego.getFechaCreacion();
		this.sinopsis = videojuego.getSinopsis();
		

		try {
			MultipartFile imagenPortada = videojuego.getImagenPortada();
			this.imagenPortada = (imagenPortada == null)? null : imagenPortada.getBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		this.id = null;
		this.listaReviews = new HashSet<>();
		this.tags = new HashSet<>();
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

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public byte[] getImagenPortada() {
		return imagenPortada;
	}

	public void setImagenPortada(byte[] imagenPortada) {
		this.imagenPortada = imagenPortada;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public Set<Review> getListaReviews() {
		return listaReviews;
	}

	public void setListaReviews(Set<Review> listaReviews) {
		this.listaReviews = listaReviews;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
}
