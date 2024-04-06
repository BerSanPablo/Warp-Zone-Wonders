package com.review.tfg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.review.tfg.dto.usuario.response.UsuarioSimplificadoResponse;
import com.review.tfg.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@CrossOrigin
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;

	@GetMapping("/")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UsuarioSimplificadoResponse>> getUsuarios(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(usuarioService.obtenerUsuarios());
    }

	@GetMapping("/roles")
    public ResponseEntity<?> getRoles(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(userDetails.getAuthorities());
    }
}
