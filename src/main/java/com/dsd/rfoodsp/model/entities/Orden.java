package com.dsd.rfoodsp.model.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orden")
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orden")
    private Integer idOrden;

    @Column(nullable = false)
    private boolean entregado;

    @Column(nullable = false)
    private boolean pagado;

    private Date fecha;

    private String  observaciones;
    private boolean activo;

    /** Puede ser el mesero. varias ordenes, un solo mesero responsable */
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;


    /** varias ordenes, una mesa (posiblemente a futuro cambie) */
    @ManyToOne
    @JoinColumn(name = "id_mesa", nullable = false)
    private Mesa mesa;


    // Bidireccion con tablas asociadas
    /* 1. mappedBy = "orden"
    Esto indica que la relación es bidireccional y que el lado inverso de la relación 
     (en la clase DetalleOrden) tiene el campo llamado orden. 
     
     2. cascade = CascadeType.ALL
    Este atributo define cómo se propagan las operaciones (persistir, actualizar, eliminar) 
     desde la entidad principal (Orden) hacia las entidades relacionadas (DetalleOrden en este caso).

     3. fetch = FetchType.LAZY
    Esto define cómo se cargan los detalles relacionados desde la base de datos:

    FetchType.LAZY (perezoso):
    No carga los DetalleOrden inmediatamente al consultar una Orden.
    Los detalles se cargan solo cuando los accedes explícitamente, por ejemplo, con orden.getDetalles(). 
        Esto mejora el rendimiento, especialmente cuando no necesitas siempre los detalles.
    
    FetchType.EAGER (ansioso):
    Carga automáticamente los detalles relacionados junto con la orden. Puede ser útil si casi siempre 
        necesitas los detalles al trabajar con una orden, pero podría reducir el rendimiento en consultas grandes.
    */
    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DetalleOrden> detalles;

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Venta> ventas;

    
    public Orden() {}

    public Orden(boolean entregado, boolean pagado, String observaciones, boolean activo, Usuario idUsuario,
            Mesa idMesa) {
        this.entregado = entregado;
        this.pagado = pagado;
        this.observaciones = observaciones;
        this.activo = activo;
        this.usuario = idUsuario;
        this.mesa = idMesa;
    }



    public Integer getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Integer idOrden) {
        this.idOrden = idOrden;
    }

    public boolean isEntregado() {
        return entregado;
    }

    public void setEntregado(boolean entregado) {
        this.entregado = entregado;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

}
