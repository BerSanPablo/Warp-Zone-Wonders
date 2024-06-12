package com.review.tfg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.review.tfg.dto.review.request.ReviewCreateDTO;
import com.review.tfg.dto.review.response.ReviewCompletaResponse;
import com.review.tfg.dto.review.response.ReviewResponse;
import com.review.tfg.service.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
@CrossOrigin
public class ReviewController {
	
	@Autowired
	ReviewService reviewService;
	
	@GetMapping("")
    public ResponseEntity<List<ReviewCompletaResponse>> getReviews() {
		
		return ResponseEntity.ok(reviewService.getAllReviews());
	}
	
	@GetMapping("/{nombreVideojuego}")
    public ResponseEntity<List<ReviewResponse>> getReviewsFiltrado(@PathVariable String nombreVideojuego) {
		return ResponseEntity.ok(reviewService.getReviewsByGame(nombreVideojuego));
	}
	
	@PostMapping("")
    public ResponseEntity<ReviewResponse> postReview(@RequestBody(required = true) ReviewCreateDTO reviewData) {
		return ResponseEntity.ok(reviewService.createReview(reviewData));
	}

}
