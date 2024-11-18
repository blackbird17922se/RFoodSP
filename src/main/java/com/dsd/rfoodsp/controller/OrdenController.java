package com.dsd.rfoodsp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    }


    /** Obtiene las ordenes que aun no han pasado por el proceso de entrega al cliente */
    @GetMapping
    public List<OrdenDTO> getOrdenesActivas(){
        return service.getOrdenesActivas();
    }


    /** Cambia el estado de la orden cuando el pedido fue entregado al cliente */
    @PutMapping("/cambiarEntregado")
    public OrdenDTO cambiarEstadoEntregado(@RequestParam Integer id){
        return service.cambiarEstadoEntregado(id);
    }

    
}
