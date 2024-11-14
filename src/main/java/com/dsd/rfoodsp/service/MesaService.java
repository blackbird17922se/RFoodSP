package com.dsd.rfoodsp.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.dsd.rfoodsp.exception.EnumManejoErrores;
import com.dsd.rfoodsp.model.dto.MesaDTO;
import com.dsd.rfoodsp.model.entities.Mesa;
import com.dsd.rfoodsp.repository.MesaRepository;

import jakarta.transaction.Transactional;

@Service
public class MesaService {

    private final MesaRepository repository;
    private final ModelMapper mapper;

    
    public MesaService(MesaRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    public MesaDTO crearMesa(MesaDTO datos){

        if(repository.findByNombre(datos.getNombre()) != null){
            throw new RuntimeException(EnumManejoErrores.MESA_DUPLICADA.getMensaje());
        }

        Mesa mesaNueva = new Mesa();

        mesaNueva.setNombre(datos.getNombre());
        mesaNueva.setDisponible(true);
        repository.save(mesaNueva);

        return mapper.map(mesaNueva, MesaDTO.class);
    }


    public List<MesaDTO> obtenerMesas(){

        List<Mesa> mesas = repository.findAll();

        //// Usamos TypeToken para mapear la lista de entidades a una lista de DTOs
        List<MesaDTO> mesasDTO = mapper.map(mesas, new TypeToken<List<MesaDTO>>(){}.getType());
   
        return mesasDTO;
    }


    public MesaDTO obtenerMesaPorNombre(String nombre){
        Mesa mesa = repository.findByNombre(nombre);
        if (mesa == null) {
            throw new RuntimeException(EnumManejoErrores.MESA_NO_ENCONTRADA.getMensaje());
        }
        return mapper.map(mesa, MesaDTO.class);
    }


    @Transactional
    public MesaDTO editarMesa(Integer id, MesaDTO datos){

        Mesa mesaEditar = repository.findByIdMesa(id);

        if (mesaEditar == null) {
            throw new RuntimeException(EnumManejoErrores.MESA_NO_ENCONTRADA.getMensaje());
        }

        mesaEditar.setNombre(datos.getNombre());
        repository.save(mesaEditar);

        return mapper.map(mesaEditar, MesaDTO.class);
    }


    /* Listar en select list */
    /* Solo lista mesas que no tengan ordenes ya hechas */


}
