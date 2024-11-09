package com.dsd.rfoodsp.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dsd.rfoodsp.config.Security.AuthResponse;
import com.dsd.rfoodsp.config.Security.JwtService;
import com.dsd.rfoodsp.model.dto.UsuarioDTO;
import com.dsd.rfoodsp.model.entities.Rol;
import com.dsd.rfoodsp.model.entities.Usuario;
import com.dsd.rfoodsp.repository.RolRepository;
import com.dsd.rfoodsp.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    
    

    public AuthResponse register(UsuarioDTO usuarioDTO) {

        // Buscar el rol y asignarlo al usuario
        Rol rol = rolRepository.findById(usuarioDTO.getRolId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        Usuario usuario = Usuario.builder()
                .nomUsuario(usuarioDTO.getNomUsuario())
                .contrasena(passwordEncoder.encode(usuarioDTO.getContrasena()))
                .nombre(usuarioDTO.getNombre())
                .apellido(usuarioDTO.getApellido())
                .rol(rol)
                .build();

        usuarioRepository.save(usuario);    // crea registro

        return AuthResponse.builder()
                .token(jwtService.getToken(usuario))
                .build();
    }


    public List<Usuario> cargarUsuarios() {
        return usuarioRepository.findAll();
    }


    



    // public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) {

    //     if (usuarioRepository.findByNomUsuario(usuarioDTO.getNomUsuario()) != null) {
    //         System.out.println("usuario existe");
    //         return null;
    //     }

    //     // Usar MapStruct para convertir de DTO a entidad
    //     Usuario usuario = UsuarioMapper.INSTANCE.toUsuario(usuarioDTO);

    //     // Buscar el rol y asignarlo al usuario
    //     Rol rol = rolRepository.findById(usuarioDTO.getRolId())
    //             .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

    //     usuario.setRol(rol);

    //     // Encriptar la contraseña antes de guardarla
    //     String contrasenaEncrypt = "prueba"
    //     // passwordEncoder.encode(usuarioDTO.getContrasena())
    //     ;
    //     usuario.setContrasena(contrasenaEncrypt);

    //     // Guardar el usuario en la base de datos
    //     Usuario usuarioGuardado = usuarioRepository.save(usuario);

    //     // Convertir de vuelta de entidad a DTO y devolver el resultado
    //     return UsuarioMapper.INSTANCE.toUsuarioDTO(usuarioGuardado);
    // }

    // public UsuarioDTO editarUsuario(Integer id, UsuarioDTO datos) {

    //     // Recuperamos el usuario desde la base de datos por su ID
    //     Usuario usuarioEditar = usuarioRepository.findByIdUsuario(id);

    //     if (usuarioEditar == null) {
    //         throw new NoSuchElementException("Usuario con ID " + id + "no encontrado");
    //     }

    //     usuarioEditar.setNombre(datos.getNombre());
    //     usuarioEditar.setApellido(datos.getApellido());

    //     return UsuarioMapper.INSTANCE.toUsuarioDTO(usuarioRepository.save(usuarioEditar));

    // }

}
