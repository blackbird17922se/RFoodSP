package com.dsd.rfoodsp.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsd.rfoodsp.model.dto.MesaDTO;
import com.dsd.rfoodsp.service.MesaService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/mesas")
public class MesaController {

    private final MesaService servicio;

    
    public MesaController(MesaService servicio) {
        this.servicio = servicio;
    }


    @PostMapping()
    public MesaDTO crearMesa(@RequestBody MesaDTO datos){
        return servicio.crearMesa(datos);
    }


    @GetMapping("")
    public List<MesaDTO> obtenerMesas() {
        return servicio.obtenerMesas();
    }


    @GetMapping("/nombre")
    public MesaDTO obtenerMesaPorNombre(@RequestParam String nombre) {
        return servicio.obtenerMesaPorNombre(nombre);
    }
    

    @PutMapping("/editar")
    public MesaDTO editarMesa(@RequestParam Integer id, @RequestBody MesaDTO datos) {
        return servicio.editarMesa(id, datos);
    }


    /* Listar en select list */
    /* Solo lista mesas que no tengan ordenes ya hechas */
    
}
