package com.review.tfg.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.review.tfg.dto.usuario.response.UsuarioSimplificadoResponse;
import com.review.tfg.entity.Usuario;
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

}
