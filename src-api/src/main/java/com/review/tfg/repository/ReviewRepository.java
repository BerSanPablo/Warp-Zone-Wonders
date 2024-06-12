package com.review.tfg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.review.tfg.entity.Review;
import com.review.tfg.entity.Usuario;
import com.review.tfg.entity.Videojuego;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
	public List<Review> findByVideojuego(Videojuego videojuego);
	public Review findByVideojuegoAndEscritor(Videojuego videojuego, Usuario escritor);
}
