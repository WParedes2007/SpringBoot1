package com.wernerparedes.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wernerparedes.webapp.biblioteca.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
