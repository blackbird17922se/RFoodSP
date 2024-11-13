package com.dsd.rfoodsp.config.Security;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dsd.rfoodsp.service.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {


    private final JwtService jwtService;
    private final CustomUserDetailsService cUserDetailsService;


    /* realiza todos los filtros relacionados al token */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
     
        
        final String token = getTokenFromRequest(request);  // obtener token
        final String username;

        // si token es null, devolver a la cadena de filtros en control
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        username = jwtService.getUsernameFromToken(token);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = cUserDetailsService.loadUserByUsername(username);

            // UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if(jwtService.isTokenValid(token, userDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails, 
                    null,
                    userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }


    /** Devuelve el token EN UN sTRING
     * @param request: alli en ese encabezado esta el token
    */
    private String getTokenFromRequest(HttpServletRequest request) {
        
        // Busca el header de autenticacion 
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        // ANALIZA si el encabezado empezara con Bearer
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            // extrae el token, descartando la palabra Bearer
            return authHeader.substring(7); 
        }
        return null;
    }

}
