package com.review.tfg.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.review.tfg.dto.auth.request.LoginRequest;
import com.review.tfg.dto.auth.request.SignUpRequest;
import com.review.tfg.dto.auth.response.TokenDTO;
import com.review.tfg.entity.Usuario;
import com.review.tfg.repository.UsuarioRepository;
import com.review.tfg.service.AuthenticationService;
import com.review.tfg.service.JwtService;

import lombok.Builder;

@Builder
@Service
public class AuthenticationServiceImpl implements AuthenticationService{

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
	
    private UsuarioRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    
    public AuthenticationServiceImpl(UsuarioRepository userRepository,
    								 PasswordEncoder passwordEncoder,
							         JwtService jwtService) {
	this.userRepo = userRepository;
	this.passwordEncoder = new BCryptPasswordEncoder(10);
	this.jwtService = jwtService;
	}

	@Override
	public TokenDTO signup(SignUpRequest request) throws IllegalArgumentException{

		Usuario usuario = new Usuario();
		usuario.setImagenPerfil(request.getImagenPerfil());
		usuario.setNick(request.getNick());
		usuario.setEmail(request.getEmail());
		usuario.setComunidad(request.getComunidad());
		usuario.setPassword(passwordEncoder.encode(request.getPassword()));
		userRepo.save(usuario);
		
		logger.info("## USUARIO CREADO CON EXITO ##");

		logger.info("## INTENTANDO GENERAR TOKEN ##");
        String jwt = jwtService.generateToken(usuario);
		logger.info("## TOKEN GENERADO ##");
        return TokenDTO.builder().token(jwt).build();
	}

	@Override
	public TokenDTO signin(LoginRequest request) {

        Usuario user = userRepo.findByEmail(request.getEmail());
        if(user == null || passwordEncoder.matches(user.getPassword(), request.getPassword())) {
        	throw new IllegalArgumentException("Email o contraseña incorrectos");
        }
        
        String jwt = jwtService.generateToken(user);
        return TokenDTO.builder().token(jwt).build();
	}

	@Override
	public TokenDTO updateToken(UserDetails userDetails) {
		String jwt = jwtService.generateToken(userDetails);
        return TokenDTO.builder().token(jwt).build();
	}
}
