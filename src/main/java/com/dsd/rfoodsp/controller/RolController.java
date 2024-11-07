package com.dsd.rfoodsp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsd.rfoodsp.entities.Rol;
import com.dsd.rfoodsp.service.RolService;

import jakarta.validation.Valid;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/roles")
@Validated
public class RolController {

    @Autowired
    private final RolService servicio;

    @Autowired
    public RolController(RolService rolService){
        this.servicio = rolService;
    }
    

    // Endpoint para obtener todos los roles
    @GetMapping()
    public List<Rol> getRoles() {
        return servicio.getRoles();
    }



    /**
     * Crea un nuevo rol.
     * @param rol El rol a crear.
     * @param result Resultados de la validación.
     * @return El rol creado.
    */
    // @Valid para habilitar las validaciones @NotBlank
    /* BindingResult result: es para poder verificar si hubo errores de validación cuando se 
    valida el objeto anotado con @Valid */
    @PostMapping
    public ResponseEntity<Rol> crearRol(@Valid @RequestBody Rol datos, BindingResult result){

        if (result.hasErrors()) {
            // Si hay errores de validación, retornamos un 400 con el mensaje de error
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // Devuelve un error 400 si hay problemas de validación
        }

        Rol rolCreado = servicio.crearRol(datos);

        return new ResponseEntity<>(rolCreado, HttpStatus.CREATED);

    }    
    
}
