package com.dsd.rfoodsp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsd.rfoodsp.model.dto.VentaDTO;
import com.dsd.rfoodsp.service.VentaService;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/venta")
public class VentaController {

    public final VentaService service;

    public VentaController(VentaService service){
        this.service = service;
    }

    @PostMapping()
    public VentaDTO setVenta(@RequestBody VentaDTO datos) {
        return service.setVenta(datos);
    }
    


    
}
