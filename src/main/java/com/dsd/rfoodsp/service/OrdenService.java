package com.dsd.rfoodsp.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.dsd.rfoodsp.exception.EnumManejoErrores;
import com.dsd.rfoodsp.model.dto.OrdenDTO;
import com.dsd.rfoodsp.model.entities.Mesa;
import com.dsd.rfoodsp.model.entities.Orden;
import com.dsd.rfoodsp.model.entities.Usuario;
import com.dsd.rfoodsp.repository.MesaRepository;
import com.dsd.rfoodsp.repository.OrdenRepository;
import com.dsd.rfoodsp.repository.UsuarioRepository;


@Service
public class OrdenService {

    private final OrdenRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final MesaRepository mesaRepository;
    private final ModelMapper mapper;


    public OrdenService(OrdenRepository repository, UsuarioRepository usuarioRepository, 
    MesaRepository mesaRepository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.usuarioRepository = usuarioRepository;
        this.mesaRepository = mesaRepository;
    }


    public OrdenDTO setOrden(OrdenDTO datos){


        Usuario usuario = usuarioRepository.findByIdUsuario(datos.getIdUsuario());
        if(usuario == null){
            throw new RuntimeException(EnumManejoErrores.USUARIO_NO_ENCONTRADO.getMensaje());
        }
        
        Mesa mesa = mesaRepository.findByIdMesa(datos.getIdMesa());
        if(mesa == null){
            throw new RuntimeException(EnumManejoErrores.MESA_NO_ENCONTRADA.getMensaje());
        }


        Orden orden = new Orden(false, false, datos.getObservaciones(),
         true, usuario, mesa);

        repository.save(orden);

        return mapper.map(orden, OrdenDTO.class);

    }
 
}
