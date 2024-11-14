package com.dsd.rfoodsp.exception;

public enum EnumManejoErrores {

    // Usuarios

    // Mesas
    MESA_DUPLICADA("El nombre de la mesa ya existe"),
    MESA_NO_ENCONTRADA("La mesa solicitada no existe"),


    // Proveedores
    PROVEEDOR_DUPLICADO("El proveedor ya está registrado"),

    // Productos
    PRODUCTO_NO_ENCONTRADO("El producto no se encontró"),
    
    // Ingredientes
    INGREDIENTE_NO_DISPONIBLE("El ingrediente no está disponible"),

    // Ordenes
    ORDEN_NO_VALIDA("La orden no es válida"),
    
    // Venta
    VENTA_FALLIDA("No se pudo procesar la venta"),

    // Generales
    ACCESO_DENEGADO("No tiene permisos para realizar esta acción");

    
    private final String mensaje;

    EnumManejoErrores(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

}
