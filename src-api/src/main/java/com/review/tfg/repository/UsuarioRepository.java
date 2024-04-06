package com.review.tfg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.review.tfg.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Usuario findByEmail(String email);
	Usuario findByNick(String nick);
	Boolean existsByEmail(String email);
	Boolean existsByNick(String nick);
}