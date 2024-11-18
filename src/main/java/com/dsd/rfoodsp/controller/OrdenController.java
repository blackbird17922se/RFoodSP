package com.dsd.rfoodsp.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsd.rfoodsp.model.dto.OrdenDTO;
import com.dsd.rfoodsp.service.OrdenService;



@RestController
@RequestMapping("/orden")
public class OrdenController {

    private final OrdenService service;

    public OrdenController(OrdenService service) {
        this.service = service;
    }

    

    @PostMapping
    public OrdenDTO setOrden(@RequestBody OrdenDTO datos){
        return service.setOrden(datos);

        // luego deberia traer el id de ese ultimo pedido

        // luego llenar la tabla detalle orden
    }

    
}
