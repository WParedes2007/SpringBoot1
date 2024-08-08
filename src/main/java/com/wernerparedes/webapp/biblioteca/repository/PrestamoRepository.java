package com.wernerparedes.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wernerparedes.webapp.biblioteca.model.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

}
