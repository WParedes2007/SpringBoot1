package com.wernerparedes.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wernerparedes.webapp.biblioteca.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {

}
