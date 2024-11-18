package com.dsd.rfoodsp.exception;

public enum EnumManejoErrores {

    // Usuarios
    USUARIO_NO_ENCONTRADO("No existe el usuario"),
    USUARIO_ID_NO_ENCONTRADO("Usuario con ID %s no encontrado"),

    // Roles
    ROL_DUPLICADO("El Rol ya existe"),
    ROL_NO_ENCONTRADO("El Rol no existe"),
    DESACT_SUPERADMIN("No se puede desactivar al superAdmin"),
    CAMBIO_SUPERADMIN("No se puede cambiar el rol del superAdmin"),

    // Mesas
    MESA_DUPLICADA("El nombre de la mesa ya existe"),
    MESA_NO_ENCONTRADA("La mesa solicitada no existe"),

    // Proveedores
    PROVEEDOR_DUPLICADO("El proveedor ya está registrado"),

    // Productos
    PRODUCTO_NO_ENCONTRADO("El producto no se encontró"),
    PRODUCTO_DUPLICADO("El producto ya existe"),
    
    // Ingredientes
    INGREDIENTE_NO_DISPONIBLE("El ingrediente no está disponible"),

    // Ordenes
    ORDEN_NO_VALIDA("La orden no es válida"),
    
    // Venta
    VENTA_FALLIDA("No se pudo procesar la venta"),

    // Generales
    ACCESO_DENEGADO("No tiene permisos para realizar esta acción"),
    CREDENCIALES_INCORRECTAS("Credenciales Incorrectas");

    
    private final String mensaje;

    EnumManejoErrores(String mensaje) {
        this.mensaje = mensaje;
    }

    // public String getMensaje() {
    //     return mensaje;
    // }

    public String getMensaje(Object... args) {
        return String.format(this.mensaje, args);
    }

    public String getMensaje() {
        return String.format(this.mensaje);
    }

}
