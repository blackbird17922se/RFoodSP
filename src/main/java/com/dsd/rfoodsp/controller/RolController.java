package com.dsd.rfoodsp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsd.rfoodsp.entities.Rol;
import com.dsd.rfoodsp.service.RolService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class RolController {

    @Autowired
    private final RolService servicio;

    @Autowired
    public RolController(RolService rolService){
        this.servicio = rolService;
    }
    

    // Endpoint para obtener todos los roles
    @GetMapping("/roles")
    public List<Rol> getRoles() {
        return servicio.getRoles();
    }
    
    
}
