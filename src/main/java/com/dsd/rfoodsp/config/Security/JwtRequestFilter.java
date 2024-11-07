package com.dsd.rfoodsp.config.Security;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dsd.rfoodsp.entities.Usuario;
import com.dsd.rfoodsp.service.UsuarioService;

import java.io.IOException;

// @Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    // @Autowired
    // private UsuarioService usuarioService;

    @Lazy
    private final UsuarioService usuarioService;

    public JwtRequestFilter(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request,
            jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain)
            throws jakarta.servlet.ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.getUsernameFromToken(jwt);
        }

        Usuario us = new Usuario();
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (jwtUtil.validateToken(jwt, username)) {
                // Autenticación exitosa, continuar con la solicitud
                UserDetails userDetails = usuarioService.cargarUsuarioNomUsuario(username); // Cargar detalles del usuario
                // UserDetails userDetails = (UserDetails) usuarioService.cargarUsuarioNomUsuario(username); // Cargar detalles del usuario

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication); // Establecer el usuario autenticado en el contexto


            }
        }
        filterChain.doFilter(request, response);

    }

}
