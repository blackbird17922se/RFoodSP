package com.dsd.rfoodsp.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsd.rfoodsp.entities.Rol;
import com.dsd.rfoodsp.repository.RolRepository;

@Service
public class RolService {

    @Autowired
    private final RolRepository rolRepository;


    @Autowired
    public RolService(RolRepository rolRepository){
        this.rolRepository = rolRepository;
    }

    /** Método para obtener todos los roles */
    public List<Rol> getRoles(){
        return rolRepository.findAll();
    }


    /**
     * Crea un nuevo rol.
     * @param rol El rol a crear.
     * @return El rol creado.
     */
    public Rol crearRol(Rol rol){
        if(rolRepository.findByNombre(rol.getNombre()) != null){
            System.out.println("rol ya existe");
            return null;
        }
        return rolRepository.save(rol);
    }
    
}
