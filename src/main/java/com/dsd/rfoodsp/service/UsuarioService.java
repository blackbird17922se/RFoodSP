package com.dsd.rfoodsp.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dsd.rfoodsp.exception.EnumManejoErrores;
import com.dsd.rfoodsp.model.dto.UsuarioDTO;
import com.dsd.rfoodsp.model.entities.Rol;
import com.dsd.rfoodsp.model.entities.Usuario;
import com.dsd.rfoodsp.repository.RolRepository;
import com.dsd.rfoodsp.repository.UsuarioRepository;
import com.dsd.rfoodsp.responses.UsuarioRest;

import jakarta.transaction.Transactional;


@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    private final String SUPERADMIN = "superAdmin";

    @Autowired
    private ModelMapper modelMapper;
    
    
    
    public UsuarioService(UsuarioRepository usuarioRepository, RolRepository rolRepository, 
        PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }


    /** Registrar un nuevo usuario con un rol asignado */
    public UsuarioRest registrarUsuario(UsuarioDTO usuarioDTO) {

        // Buscar el rol por su ID
        Rol rol = rolRepository.findById(usuarioDTO.getRolId())
                .orElseThrow(() -> new RuntimeException(EnumManejoErrores.ROL_NO_ENCONTRADO.getMensaje()));

        // Crear el nuevo usuario
        Usuario usuario = Usuario.builder()
                .nomUsuario(usuarioDTO.getNomUsuario())
                .contrasena(passwordEncoder.encode(usuarioDTO.getContrasena()))
                .nombre(usuarioDTO.getNombre())
                .apellido(usuarioDTO.getApellido())
                .rol(rol)
                .activo(true)
                .build();

        usuarioRepository.save(usuario);
        return modelMapper.map(usuario, UsuarioRest.class);

    }


    /** Obtener todos los usuarios junto con sus roles */
    public List<Usuario> cargarUsuarios() {
        return usuarioRepository.findAllUsuarioConRoles();
    }


    /** Obtener los datos de un usuario por su ID */
    public UsuarioRest cargarDatosUsuario(Integer id) {
        Usuario usuario = consultarExistenciaUsuario(id);
        return modelMapper.map(usuario, UsuarioRest.class);
    }


    /** Editar nombre y apellido del usuario */
    // Esta anotación asegura que si algo falla durante la actualización, los cambios se 
    // revertirán automáticamente (rollback), protegiendo la integridad de los datos.
    @Transactional
    public UsuarioRest editarUsuario(Integer id, UsuarioDTO datos) {

        Usuario usuarioEditar = consultarExistenciaUsuario(id);

        usuarioEditar.setNombre(datos.getNombre());
        usuarioEditar.setApellido(datos.getApellido());

        usuarioRepository.save(usuarioEditar);

        return modelMapper.map(usuarioEditar, UsuarioRest.class);
    }


    /** Cambiar estado de un usuario (activar/desactivar) */
    @Transactional
    public UsuarioRest cambiarEstadoUsuario(Integer id, UsuarioDTO datos) {

        // si el usuario es el superAdmin. NO puede ser desactivao
        Usuario usuario = consultarExistenciaUsuario(id);

        if (SUPERADMIN.equals(usuario.getRol().getNombre())) {
            throw new IllegalArgumentException(EnumManejoErrores.DESACT_SUPERADMIN.getMensaje());
        }

        usuario.setActivo(datos.isActivo());
        usuarioRepository.save(usuario);
        return modelMapper.map(usuario, UsuarioRest.class);
    }

    
    /** Cambiar el rol de un usuario */
    @Transactional
    public UsuarioRest cambiarRolUsuario(Integer id, UsuarioDTO datos) {

        Usuario usuario = consultarExistenciaUsuario(id);
        
        if (SUPERADMIN.equals(usuario.getRol().getNombre())) {
            throw new IllegalArgumentException(EnumManejoErrores.CAMBIO_SUPERADMIN.getMensaje());
        }

        Rol nuevRol = rolRepository.findById(datos.getRolId())
            .orElseThrow(() -> new RuntimeException(EnumManejoErrores.ROL_NO_ENCONTRADO.getMensaje()));

        usuario.setRol(nuevRol);
        usuarioRepository.save(usuario);
        return modelMapper.map(usuario, UsuarioRest.class); 
    }


    /** Verificar si el usuario existe por su ID */
    public Usuario consultarExistenciaUsuario(Integer id){

        Usuario datos = usuarioRepository.findByIdUsuario(id);

        if(datos == null){
            throw new NoSuchElementException(EnumManejoErrores.USUARIO_ID_NO_ENCONTRADO.getMensaje(id));
        }
        return datos;
    }

}