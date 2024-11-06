package com.dsd.rfoodsp.service;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dsd.rfoodsp.dto.UsuarioDTO;
import com.dsd.rfoodsp.entities.Rol;
import com.dsd.rfoodsp.entities.Usuario;
import com.dsd.rfoodsp.mapper.UsuarioMapper;
import com.dsd.rfoodsp.repository.RolRepository;
import com.dsd.rfoodsp.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private final RolRepository rolRepository;

    // @Autowired
    // private BCryptPasswordEncoder passwordEncoder;

    


    public UsuarioService(UsuarioRepository usuarioRepository, RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }




    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO ){

        if(usuarioRepository.findByNomUsuario(usuarioDTO.getNomUsuario()) != null){
            System.out.println("usuario existe");
            return null;
        }

        // Usar MapStruct para convertir de DTO a entidad
        Usuario usuario = UsuarioMapper.INSTANCE.toUsuario(usuarioDTO);

        // Buscar el rol y asignarlo al usuario
        Rol rol = rolRepository.findById(usuarioDTO.getRolId())
            .orElseThrow(()-> new RuntimeException("Rol no encontrado"));

        usuario.setRol(rol);

        // Encriptar la contraseña antes de guardarla
        // String contrasenaEncrypt = passwordEncoder.encode(usuarioDTO.getContrasena());
        // usuario.setContrasena(contrasenaEncrypt);

        // Guardar el usuario en la base de datos
        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        // Convertir de vuelta de entidad a DTO y devolver el resultado
        return UsuarioMapper.INSTANCE.toUsuarioDTO(usuarioGuardado);
    }
    
}
