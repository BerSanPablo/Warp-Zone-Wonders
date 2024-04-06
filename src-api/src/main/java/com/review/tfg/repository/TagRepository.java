package com.review.tfg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.review.tfg.entity.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
	public Tag findByNombre(String nombre);
}
