package com.dsd.rfoodsp.model.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/** Representa las mesas del restaurante, de las cuales se realizara las ordenes */
@Entity
@Table(name = "mesa")
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mesa", nullable = false)
    private Integer idMesa;

    private String nombre;

    @Column(nullable = false)
    private boolean disponible;



    // Getters y Setters
    public Integer getIdMesa() {
        return idMesa;
    }
    public void setIdMesa(Integer idMesa) {
        this.idMesa = idMesa;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public boolean isDisponible() {
        return disponible;
    }
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

}
