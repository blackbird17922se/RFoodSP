package com.dsd.rfoodsp.model.dto;

import java.time.LocalDateTime;

import com.dsd.rfoodsp.model.entities.Orden;
import com.dsd.rfoodsp.model.entities.Usuario;


public class VentaDTO {

    private Integer id_venta;
    private LocalDateTime fecha;
    private Integer total;
    private boolean activo;
    private Integer idUsuario;
    private Integer idOrden;

    
    public Integer getId_venta() {
        return id_venta;
    }

    public void setId_venta(Integer id_venta) {
        this.id_venta = id_venta;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Integer idOrden) {
        this.idOrden = idOrden;
    }


    
}
