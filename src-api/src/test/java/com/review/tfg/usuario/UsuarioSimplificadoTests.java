package com.review.tfg.usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.review.tfg.dto.usuario.response.UsuarioSimplificadoResponse;
import com.review.tfg.entity.Usuario;


public class UsuarioSimplificadoTests {

	@Test
	void constructorConEntidad() {
		Usuario usuario = new Usuario();
		usuario.setNick("nickTest");
		byte[] imagen = "imagenTest".getBytes();
		usuario.setImagenPerfil(imagen);
		
		UsuarioSimplificadoResponse usuarioSimplificado = new UsuarioSimplificadoResponse(usuario);

		assertEquals("nickTest", usuarioSimplificado.getNick(), "El nick no coincide");
		assertEquals(imagen, usuarioSimplificado.getImagenPerfil(), "La imagen de perfil no coincide");
	}
}
