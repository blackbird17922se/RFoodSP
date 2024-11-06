package com.dsd.rfoodsp.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsd.rfoodsp.dto.UsuarioDTO;
import com.dsd.rfoodsp.entities.Usuario;
import com.dsd.rfoodsp.mapper.UsuarioMapper;
import com.dsd.rfoodsp.responses.UsuarioRest;
import com.dsd.rfoodsp.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private final UsuarioService servicio;

    public UsuarioController(UsuarioService usuarioService) {
        this.servicio = usuarioService;
    }


    @GetMapping
    public List<UsuarioRest> cargarUsuarios(){


        List<Usuario> usuarios = servicio.cargarUsuarios();
        return UsuarioMapper.INSTANCE.usuariosToUsuarioRests(usuarios);
        

        // Mapear la lista de usuarios a una lista de UsuarioRest
        // List<UsuarioRest> usuarioRests = usuarios.stream()
        //     .map(u -> {
        //         UsuarioRest uRest = new UsuarioRest();
        //         uRest.setNombre(u.getNombre());
        //         uRest.setApellido(u.getApellido());
        //         uRest.setNomUsuario(u.getNomUsuario());
        //         uRest.setRolId(u.getRol().getId_rol());
        //         return uRest;
        //     }).collect(Collectors.toList()); //Collectors.toList() convierte el stream de objetos UsuarioRest en una lista.

        // return usuarioRests;  
    }



    @PostMapping
    public ResponseEntity<UsuarioRest> crearUsuario(@Valid @RequestBody UsuarioDTO datos, 
        BindingResult result){

        if(result.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        UsuarioDTO usuarioCreado = servicio.crearUsuario(datos);
        UsuarioRest usuarioRest = new UsuarioRest();

        BeanUtils.copyProperties(usuarioCreado, usuarioRest);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRest);
    }
   
}
