package com.review.tfg.controller;

import java.util.Date;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.review.tfg.dto.auth.request.LoginRequest;
import com.review.tfg.dto.auth.request.SignUpRequest;
import com.review.tfg.dto.auth.response.TokenDTO;
import com.review.tfg.dto.videojuego.request.VideojuegoCreateDTO;
import com.review.tfg.dto.videojuego.response.VideojuegoResponse;
import com.review.tfg.service.AuthenticationService;
import com.review.tfg.service.UsuarioService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
    AuthenticationService authenticationService;
	
    @PostMapping("/signup")
    public ResponseEntity<TokenDTO> signup(
			@RequestParam("nick") String nick,
            @RequestParam("email") String email,
            @RequestParam("comunidad") String comunidad,
            @RequestParam("password") String password,
            @RequestParam(required = false, name= "imagenPerfil") MultipartFile imagenPerfil) {
    	
    	SignUpRequest request = new SignUpRequest(password, imagenPerfil, nick, email, comunidad);
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }

    @PostMapping("/loginToken")
    public ResponseEntity<TokenDTO> loginToken(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(authenticationService.updateToken(userDetails));
    }

    @PostMapping("/checkNombre")
    public ResponseEntity<Boolean> checkNombre(@RequestBody String nombre) {
        return ResponseEntity.ok(usuarioService.checkName(nombre));
    }

    @PostMapping("/checkMail")
    public ResponseEntity<Boolean> checkMail(@RequestBody String mail) {
        return ResponseEntity.ok(usuarioService.checkMail(mail));
    }
}
