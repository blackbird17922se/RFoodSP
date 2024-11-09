package com.dsd.rfoodsp.config.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {


    private final JwtAuthFilter jwtAuthFilter; // Inyectar el JwtAuthFilter
    private final AuthenticationProvider authProvider;  // Inyectar el AuthenticationProvider

    // metodo q restringe acceso a rutas
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        return http
            .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF
            // rutas privadas y protegidas
            .authorizeHttpRequests(authReq -> authReq
                    .requestMatchers("/usuarios/login").permitAll() // request publicos
                    .anyRequest().authenticated() // demas request protegidos
            )
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authProvider)  // Agregar el AuthenticationProvider
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .build();  // Agregar el filtro JWT

    }

}
