package com.review.tfg.service;

import java.util.List;

import com.review.tfg.dto.review.request.ReviewCreateDTO;
import com.review.tfg.dto.review.response.ReviewCompletaResponse;
import com.review.tfg.dto.review.response.ReviewResponse;

public interface ReviewService {
	ReviewResponse createReview(ReviewCreateDTO review);
	List<ReviewCompletaResponse> getAllReviews();
	List<ReviewResponse> getReviewsByGame(String nombreVideojuego);
}
