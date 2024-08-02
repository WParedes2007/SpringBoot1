package com.wernerparedes.webapp.biblioteca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
@Table(name = "Clientes")
public class Cliente {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long dpi;
    //@NotNull(message = "El Nombre No Puede Ser Nulo")
    private String nombre;
    private String apellido;
    private String telefono;

}
