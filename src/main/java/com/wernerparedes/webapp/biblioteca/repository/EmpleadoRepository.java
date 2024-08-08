package com.wernerparedes.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wernerparedes.webapp.biblioteca.model.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{

}
