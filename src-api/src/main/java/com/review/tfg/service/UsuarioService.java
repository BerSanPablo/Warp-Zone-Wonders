package com.review.tfg.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.review.tfg.dto.usuario.response.UsuarioSimplificadoResponse;

public interface UsuarioService {
	UserDetailsService userDetailsService();
	List<UsuarioSimplificadoResponse> obtenerUsuarios();
}
