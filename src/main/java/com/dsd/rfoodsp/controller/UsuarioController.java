package com.dsd.rfoodsp.controller;

import java.util.List;

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

import com.dsd.rfoodsp.config.Security.AuthResponse;
import com.dsd.rfoodsp.config.Security.LoginRequest;
import com.dsd.rfoodsp.dto.UsuarioDTO;
import com.dsd.rfoodsp.entities.Usuario;
import com.dsd.rfoodsp.mapper.UsuarioMapper;
import com.dsd.rfoodsp.responses.UsuarioRest;
import com.dsd.rfoodsp.service.UsuarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService servicio;


    // @GetMapping
    // public List<UsuarioRest> cargarUsuarios(){

    //     List<Usuario> usuarios = servicio.cargarUsuarios();
    //     return UsuarioMapper.INSTANCE.usuariosToUsuarioRests(usuarios);

    // }


    // nuevo
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest datos){

        return ResponseEntity.ok(
            servicio.login(datos)
        );
    }


    @PostMapping("/registro")
    public ResponseEntity<AuthResponse> registro(@RequestBody UsuarioDTO entity) {

        return ResponseEntity.ok(
            servicio.register(entity)
        );
    }



    // @PostMapping
    // public ResponseEntity<UsuarioRest> crearUsuario(@Valid @RequestBody UsuarioDTO datos, 
    //     BindingResult result){

    //     if(result.hasErrors()){
    //         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    //     }

    //     UsuarioDTO usuarioCreado = servicio.crearUsuario(datos);
    //     UsuarioRest usuarioRest = new UsuarioRest();

    //     BeanUtils.copyProperties(usuarioCreado, usuarioRest);

    //     return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRest);
    // }

    
   

    // @PutMapping("{id}")
    // public ResponseEntity<UsuarioRest> actualizarUsuario(@PathVariable Integer id, @RequestBody UsuarioDTO entity) {

    //     UsuarioDTO rt =servicio.editarUsuario(id, entity);
        
    //     UsuarioRest ur = new UsuarioRest();
    //     BeanUtils.copyProperties(rt, ur);
    //     return ResponseEntity.status(HttpStatus.OK).body(ur);
    // }
}
