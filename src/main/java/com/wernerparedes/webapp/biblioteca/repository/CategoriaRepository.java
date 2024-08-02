package com.wernerparedes.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wernerparedes.webapp.biblioteca.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    
}
