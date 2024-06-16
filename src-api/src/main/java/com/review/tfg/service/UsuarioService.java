package com.review.tfg.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import com.review.tfg.dto.usuario.response.UsuarioSimplificadoResponse;
import com.review.tfg.entity.Usuario;

public interface UsuarioService {
	Usuario getUserByName(String name);
	Usuario updateUser(String name, String comunidad, MultipartFile imagenPortada);
	boolean checkName(String name);
	boolean checkMail(String mail);
	UserDetailsService userDetailsService();
	List<UsuarioSimplificadoResponse> obtenerUsuarios();
}
