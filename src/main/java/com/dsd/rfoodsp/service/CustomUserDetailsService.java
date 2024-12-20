package com.dsd.rfoodsp.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dsd.rfoodsp.exception.EnumManejoErrores;
import com.dsd.rfoodsp.model.entities.Usuario;
import com.dsd.rfoodsp.repository.UsuarioRepository;

import org.springframework.security.core.userdetails.User;


/** De mau */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNomUsuario(username)
            .orElseThrow(() -> new UsernameNotFoundException(EnumManejoErrores.USUARIO_NO_ENCONTRADO.getMensaje()));

        // Retorna un UserDetails con la contraseña cifrada y los roles (si los tienes)
        return new User(usuario.getNomUsuario(), usuario.getContrasena(), new ArrayList<>());
    }
}

