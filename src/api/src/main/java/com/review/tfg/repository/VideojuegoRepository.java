package com.review.tfg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.review.tfg.entity.Videojuego;

@Repository
public interface VideojuegoRepository extends JpaRepository<Videojuego, Long> {
	public Videojuego findByNombre(String nombre);
	
	//Filtrado
	List<Videojuego> findByNombreLike(String nombre);
	List<Videojuego> findByTagsNombreIn(String[] tags);
	List<Videojuego> findByNombreLikeAndTagsNombreIn(String nombre, String[] tagsNombre);
}

