package com.review.tfg.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.review.tfg.dto.usuario.response.UsuarioSimplificadoResponse;
import com.review.tfg.dto.videojuego.response.VideojuegoResponse;
import com.review.tfg.entity.Usuario;
import com.review.tfg.entity.Videojuego;
import com.review.tfg.error.exception.UserNotFoundException;
import com.review.tfg.repository.UsuarioRepository;
import com.review.tfg.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	UsuarioRepository repo;

	@Override
	public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
            	Usuario user = repo.findByNick(username);
            	
            	if (user == null) {
            		throw new UserNotFoundException("No se ha encontrado al usuario con nombre " + username);
            	}
                return user;
            }
        };
    }

	@Override
	public List<UsuarioSimplificadoResponse> obtenerUsuarios() {
		return repo.findAll()
					.stream()
					.map(UsuarioSimplificadoResponse::new)
					.collect(Collectors.toList());
	}

	@Override
	public Usuario getUserByName(String name) {
		return repo.findByNick(name);
	}

	@Override
	public boolean checkName(String name) {
		return !repo.existsByNick(name);
	}

	@Override
	public boolean checkMail(String mail) {
		return !repo.existsByEmail(mail);
	}

	@Override
	public Usuario updateUser(String name, String comunidad, MultipartFile imagenPortada) {
		
		Usuario user = repo.findByNick(name);
		user.setComunidad(comunidad);
		
		try {
			if(imagenPortada != null) {
				user.setImagenPerfil(imagenPortada.getBytes());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return repo.save(user);
	}

}
