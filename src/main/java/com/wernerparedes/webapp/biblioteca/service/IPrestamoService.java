package com.wernerparedes.webapp.biblioteca.service;

import java.util.List;

import com.wernerparedes.webapp.biblioteca.model.Libro;
import com.wernerparedes.webapp.biblioteca.model.Prestamo;
import com.wernerparedes.webapp.biblioteca.util.MethodType;

public interface IPrestamoService {
    public List<Prestamo>listarPrestamos();

    public Integer guardarPrestamo(Prestamo prestamo, MethodType methodType);

    public Prestamo buscarPrestamoPorId(Long id);

    public void eliminarPrestamo(Prestamo prestamo);

    public Boolean verificarCliente(Prestamo newPrestamo);

    public Boolean verificarLibro(Prestamo newPrestamo, Prestamo prestamo);

    public Boolean verificarCantidad(Prestamo newPrestamo);

    public void cambiarDisponibilidad(List<Libro> libros, Boolean disponibilidad);

    public void librosRegresados(Prestamo prestamo, Prestamo newPrestamo);

    
}
