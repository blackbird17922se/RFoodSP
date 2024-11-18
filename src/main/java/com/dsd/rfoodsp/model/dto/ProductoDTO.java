package com.dsd.rfoodsp.model.dto;


public class ProductoDTO {

    private Integer idProducto;
    private String nombre;
    private Integer precio;
    private boolean activo;


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
        return "ProductoDTO{nombre=" + nombre + ", precio=" + precio + ", activo=" + activo + "}";
    }

}
