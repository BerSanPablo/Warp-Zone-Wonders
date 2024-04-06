package com.review.tfg.service;

import java.util.List;

import com.review.tfg.dto.videojuego.request.VideojuegoCreateDTO;
import com.review.tfg.dto.videojuego.response.VideojuegoResponse;

public interface VideojuegoService {
	VideojuegoResponse createVideojuego(VideojuegoCreateDTO videojuego);
	VideojuegoResponse getVideojuegoByName(String nombre);
	List<VideojuegoResponse> getVideojuegos(String nombre, String[] tags);
}
