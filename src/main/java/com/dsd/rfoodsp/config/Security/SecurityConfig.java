package com.dsd.rfoodsp.config.Security;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.dsd.rfoodsp.service.UsuarioService;

@Configuration
public class SecurityConfig{

    // @Lazy
    // @Autowired
    // private JwtRequestFilter jwtFiltro;


    // @Autowired
    // private UsuarioService usuarioService;

    private final ObjectProvider<UsuarioService> usuarioServiceProvider;

    public SecurityConfig(ObjectProvider<UsuarioService> usuarioServiceProvider) {
        this.usuarioServiceProvider = usuarioServiceProvider;
    }

    // @Lazy
    // private final UsuarioService usuarioService;

    // public SecurityConfig(UsuarioService usuarioService) {
    //     this.usuarioService = usuarioService;
    // }

    @Bean
    public JwtRequestFilter jwtFiltro() {
        return new JwtRequestFilter(usuarioServiceProvider.getIfAvailable());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(request -> request
                .requestMatchers("/usuarios/login")
                .permitAll()

                .anyRequest()
                .authenticated()


                // .requestMatchers("/usuarios/**")
                // .authenticated()
                // .anyRequest()
                // .authenticated()

                )
                .addFilterBefore(jwtFiltro(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
