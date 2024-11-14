package com.dsd.rfoodsp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.dsd.rfoodsp.config.Security.AuthResponse;
import com.dsd.rfoodsp.config.Security.JwtService;
import com.dsd.rfoodsp.exception.EnumManejoErrores;
import com.dsd.rfoodsp.model.dto.UsuarioDTO;

import lombok.RequiredArgsConstructor;


/** Servicio encargado del Login de la app. Hace uso de JwtService y AuthenticationManager*/
@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService cUserDetailsService;


    public AuthResponse login(UsuarioDTO datos) {
        
        try {
            // Autentica al usuario utilizando el `AuthenticationManager`
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(datos.getNomUsuario(), datos.getContrasena())
            );


            // Ahora carga el usuario desde la base de datos después de autenticar
            UserDetails user = cUserDetailsService.loadUserByUsername(datos.getNomUsuario());
            // usuarioRepository.findByNomUsuario(datos.getNom_usuario()).orElseThrow();
            String token = jwtService.getToken(user);
            
            return AuthResponse.builder().token(token).build();

        } catch (Exception e) {
            throw new RuntimeException(EnumManejoErrores.CREDENCIALES_INCORRECTAS.getMensaje(), e);
        }

    }
    
}
