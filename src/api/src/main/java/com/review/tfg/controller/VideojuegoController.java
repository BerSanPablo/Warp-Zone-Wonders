package com.review.tfg.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.review.tfg.dto.videojuego.request.VideojuegoCreateDTO;
import com.review.tfg.dto.videojuego.response.VideojuegoResponse;
import com.review.tfg.service.VideojuegoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/videojuego")
@RequiredArgsConstructor
@CrossOrigin
public class VideojuegoController {

	@Autowired
	VideojuegoService videojuegoService;
	
	@PostMapping("")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	//TODO - que le lleguen los tags
    public ResponseEntity<VideojuegoResponse> postVideojuego(@RequestParam("nombre") String nombre,
												            @RequestParam("fechaCreacion") String fechaCreacion,
												            @RequestParam("sinopsis") String sinopsis,
												            @RequestParam(required = false, name= "imagenPortada") MultipartFile imagenPortada) {
		
		VideojuegoCreateDTO videojuego = new VideojuegoCreateDTO(nombre,
																new Date(Long.parseLong(fechaCreacion)),
																(imagenPortada != null)? imagenPortada:null,
																sinopsis,
																new HashSet());
		
        return ResponseEntity.ok(videojuegoService.createVideojuego(videojuego));
    }
	
	@GetMapping("")
    public ResponseEntity<List<VideojuegoResponse>> getVideojuegos(@RequestParam(required = false) String nombre,
			  													   @RequestParam(required = false) String[] tag) {
        
        return ResponseEntity.ok(videojuegoService.getVideojuegos(nombre, tag));
    }
	
	@GetMapping("/{nombre}")
    public ResponseEntity<VideojuegoResponse> getVideojuegoByName(@PathVariable String nombre) {
        return ResponseEntity.ok(videojuegoService.getVideojuegoByName(nombre));
    }
}
