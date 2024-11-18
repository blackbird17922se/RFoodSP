package com.dsd.rfoodsp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dsd.rfoodsp.model.dto.ProductoDTO;
import com.dsd.rfoodsp.service.ProductoService;



@RestController
@RequestMapping("/producto")
public class ProductoController {

    private final ProductoService servicio;


    public ProductoController(ProductoService servicio) {
        this.servicio = servicio;
    }



    @PostMapping()
    public ProductoDTO crearProducto(@RequestBody ProductoDTO datos){
        return servicio.crearProducto(datos);
    }


    @GetMapping
    public List<ProductoDTO> getProductos(){
        return servicio.getProductos();
    }


    @PutMapping
    public ProductoDTO editarProducto(@RequestParam Integer id, @RequestBody ProductoDTO datos){
        return servicio.editarProducto(id, datos);
    }


    @PutMapping("/cambiarEstado")
    public ProductoDTO cambiarEstadoProducto(@RequestParam Integer id){
        return servicio.cambiarEstadoProducto(id);
    }
    
}
