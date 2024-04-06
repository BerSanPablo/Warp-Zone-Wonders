package com.review.tfg.usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

import com.review.tfg.dto.usuario.response.UsuarioDetallesResponse;
import com.review.tfg.entity.Usuario;

public class UsuarioDetallesTests {

	@Test
	void constructorConEntidad() {
		Usuario usuario = new Usuario();
		usuario.setNick("nickTest");
		byte[] imagen = "imagenTest".getBytes();
		usuario.setImagenPerfil(imagen);
		usuario.setEmail("emailTest");
		usuario.setComunidad("comunidadTest");
		Date fechaCreacion = new Date();
		usuario.setFechaCreacion(fechaCreacion);
		
		UsuarioDetallesResponse usuarioDetalle = new UsuarioDetallesResponse(usuario);

		assertEquals("nickTest", usuarioDetalle.getNick(), "El nick no coincide");
		assertEquals(imagen, usuarioDetalle.getImagenPerfil(), "La imagen de perfil no coincide");
		assertEquals("emailTest", usuario.getEmail(), "El email no coincide");
		assertEquals("comunidadTest", usuario.getComunidad(), "La comunidad no coincide");
		assertEquals(fechaCreacion, usuario.getFechaCreacion(), "La fecha de creacion no coincide");
	}
}
