package com.wernerparedes.webapp.biblioteca.service;

import java.util.List;

import com.wernerparedes.webapp.biblioteca.model.Prestamo;

public interface IPrestamoService {
    public List<Prestamo>listarPrestamos();

    public Prestamo guardarPrestamo(Prestamo prestamo);

    public Prestamo buscarPrestamoPorId(Long id);

    public void eliminarPrestamo(Prestamo prestamo);
}
