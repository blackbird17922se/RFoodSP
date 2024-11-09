package com.dsd.rfoodsp.config.Security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //geter y seters
@Builder //contruir objetos
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    String nom_usuario;
    String contrasena;
    
}
