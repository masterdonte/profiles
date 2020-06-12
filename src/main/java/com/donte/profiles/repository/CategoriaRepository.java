package com.donte.profiles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.donte.profiles.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
