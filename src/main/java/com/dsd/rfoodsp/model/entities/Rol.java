package com.dsd.rfoodsp.model.entities;

import jakarta.validation.constraints.NotBlank;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Esta clase representa un Rol en el sistema.
 * Contiene información relacionada con los roles que los usuarios pueden tener.
 * Cada rol tiene un nombre único y un identificador.
 */
@Entity
public class Rol {

    /*strategy = GenerationType.IDENTITY:
    Esta estrategia le dice a la base de datos que la generación del ID será manejada 
    por la base de datos misma, utilizando una columna auto-incrementable. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_rol;

    /** El nombre del rol. Este campo no puede estar vacío. */
    // @NotBlank valida que el campo no sea null ni una cadena vacía.
    /* No necesitas escribir código adicional para validar manualmente si un campo está 
    vacío. Solo debes aplicar las anotaciones de validación a los atributos de tu entidad 
    y usar @Valid en el controlador para que la validación se ejecute automáticamente. */
    @NotBlank(message = "El nombre del rol es obligatorio")
    private String nombre;



    // Getters y Setters
    public Integer getId_rol() {
        return id_rol;
    }

    public void setId_rol(Integer id_rol) {
        this.id_rol = id_rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
  
}
