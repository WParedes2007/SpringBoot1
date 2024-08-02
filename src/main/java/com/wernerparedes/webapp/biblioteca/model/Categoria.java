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
@Table(name = "Categorias")
public class Categoria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //@NotNull(message = "El Nombre De La Categoria No Puede Ser Nulo")
    private String nombreCategoria;
    
    
}
