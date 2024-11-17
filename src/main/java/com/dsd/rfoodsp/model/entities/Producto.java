package com.dsd.rfoodsp.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/** Representacion de los productos o platos a la venta al cliente */
@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto", nullable = false)
    private Integer idProducto;

    @Column(nullable = false)
    private String nombre;

    private Integer precio;

    private boolean activo;


    public Producto() {};



    public Producto(String nombre, Integer precio, boolean activo) {
        this.nombre = nombre;
        this.precio = precio;
        this.activo = activo;
    }


    public Integer getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPrecio() {
        return precio;
    }
    public void setPrecio(Integer precio) {
        this.precio = precio;
    }
    public boolean isActivo() {
        return activo;
    }
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Producto{id=" + idProducto + ", nombre='" + nombre + "', activo=" + activo + ", precio=" + precio + "}";
    }

}
