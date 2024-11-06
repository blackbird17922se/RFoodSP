package com.dsd.rfoodsp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.dsd.rfoodsp.dto.UsuarioDTO;
import com.dsd.rfoodsp.entities.Usuario;

@Mapper
public interface UsuarioMapper {
    
    // Instancia del mapper
    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    // Mapea Usuario a UsuarioDTO
    /** indica que el campo rol.id de Usuario debe mapearse al campo rolId de UsuarioDTO. */ 
    @Mapping(source = "rol.id_rol", target = "rolId")
    UsuarioDTO toUsuarioDTO(Usuario usuario);

    // Mapea UsuarioDTO a Usuario
    @Mapping(source = "rolId", target = "rol.id_rol")
    Usuario toUsuario(UsuarioDTO usuarioDTO);
}
