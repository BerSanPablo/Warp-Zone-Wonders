package com.review.tfg.usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.review.tfg.entity.Usuario;

public class UsuarioTests {

	@Test
	void getterSetter() {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setNick("nickTest");
		usuario.setEmail("emailTest");

		assertEquals(1L, usuario.getId(), "El ID no coincide");
		assertEquals("nickTest", usuario.getNick(), "El nick no coincide");
		assertEquals("emailTest", usuario.getEmail(), "El email no coincide");
	}
}
