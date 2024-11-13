package com.dsd.rfoodsp.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dsd.rfoodsp.model.dto.UsuarioDTO;
import com.dsd.rfoodsp.responses.UsuarioRest;
import com.dsd.rfoodsp.service.UsuarioService;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService servicio;

    @Autowired
    ModelMapper modelMap;



    public UsuarioController(UsuarioService servicio) {
        this.servicio = servicio;
    }


    /**
     * Endpoint para registrar un nuevo usuario.
     * 
     * @param entity Datos del usuario a registrar.
     * @return Respuesta con el token de autenticación del usuario registrado.
     */
    @PostMapping("/registro")
    public ResponseEntity<UsuarioRest> registrarUsuario(@RequestBody UsuarioDTO entity) {
        // return ResponseEntity.ok(servicio.register(entity));
        UsuarioRest response = servicio.registrarUsuario(entity);
        return ResponseEntity.ok(response);
    }


    /**
     * Endpoint para obtener una lista de todos los usuarios.
     * @return Lista de usuarios con sus roles.
     */
    @GetMapping("/leer")
    public List<UsuarioRest> cargarUsuarios() {

        return servicio.cargarUsuarios().stream()
                .map(usuario -> {
                    // Convertir Usuario a UsuarioRest usando ModelMapper
                    UsuarioRest usuarioRest = modelMap.map(usuario, UsuarioRest.class);

                    // Asignar el nombre del rol manualmente
                    usuarioRest.setNombreRol(usuario.getRol().getNombre());

                    return usuarioRest;
                }).collect(Collectors.toList());

    }

    /** Traer los datos del usuario actual por su ID */
    @GetMapping("/datosUsuario")
    public ResponseEntity<UsuarioRest> cargarDatosUsuario(@RequestParam Integer id) {
        UsuarioRest usuarioRest = servicio.cargarDatosUsuario(id);
        return ResponseEntity.ok(usuarioRest);
    }

    /** Actualizar nombre y apellido del usuario actual */
    @PutMapping("/editar")
    public ResponseEntity<UsuarioRest> actualizarUsuario(@RequestParam Integer id, @RequestBody UsuarioDTO entity) {
        UsuarioRest usuarioActualizado = servicio.editarUsuario(id, entity);
        return ResponseEntity.ok(usuarioActualizado);
    }

    /** Activar o desactivar un usuario (excepto superAdmin) */
    @PutMapping("/estadoUsuario")
    public ResponseEntity<UsuarioRest> cambiarEstadoUsuario(@RequestParam Integer id, @RequestBody UsuarioDTO datos) {
        UsuarioRest usuarioActualizado = servicio.cambiarEstadoUsuario(id, datos);
        return ResponseEntity.ok(usuarioActualizado);
    }

    /** Cambiar de rol (o permisos) */
    @PutMapping("/cambiarRol")
    public ResponseEntity<UsuarioRest> cambiarRolUsuario(@RequestParam Integer id, @RequestBody UsuarioDTO datos) {
        UsuarioRest usuarioActualizado = servicio.cambiarRolUsuario(id, datos);
        return ResponseEntity.ok(usuarioActualizado);
    }
}
