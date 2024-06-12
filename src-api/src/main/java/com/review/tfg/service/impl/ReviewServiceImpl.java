package com.review.tfg.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.review.tfg.dto.review.request.ReviewCreateDTO;
import com.review.tfg.dto.review.response.ReviewCompletaResponse;
import com.review.tfg.dto.review.response.ReviewResponse;
import com.review.tfg.entity.Review;
import com.review.tfg.entity.Usuario;
import com.review.tfg.entity.Videojuego;
import com.review.tfg.repository.ReviewRepository;
import com.review.tfg.repository.UsuarioRepository;
import com.review.tfg.repository.VideojuegoRepository;
import com.review.tfg.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	VideojuegoRepository videojuegoRepository;
	
	@Autowired
	ReviewRepository reviewRepository;

	@Override
	public ReviewResponse createReview(ReviewCreateDTO review) {
		//Obtenemos las entidades relacionadas
		Usuario usuario = usuarioRepository.findByNick(review.getNombreUsuario());
		Videojuego videojuego = videojuegoRepository.findByNombre(review.getNombreVideojuego());
		
		//Guardamos y devolvemos
		Review response = reviewRepository.save(new Review(review, usuario, videojuego));
		return new ReviewResponse(response);
	}

	@Override
	public List<ReviewCompletaResponse> getAllReviews() {
		return reviewRepository.findAll()
								.stream()
								.map(ReviewCompletaResponse::new)
								.collect(Collectors.toList());
	}

	@Override
	public List<ReviewResponse> getReviewsByGame(String nombreVideojuego) {
		Videojuego videojuego = videojuegoRepository.findByNombre(nombreVideojuego);
		
		return reviewRepository.findByVideojuego(videojuego)
								.stream()
								.map(ReviewResponse::new)
								.collect(Collectors.toList());
	}

}
