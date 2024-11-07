package com.dsd.rfoodsp.config.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;

import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

/** Encargada de generar y validar tokens */
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private SecretKey key2 = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Genera una clave segura de 256 bits


    // Método para generar el token
    public String generateToken(String username) {

        // Convertir el secreto en una clave en formato adecuado para HS256
        // SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());

        return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 horas
        .signWith(SignatureAlgorithm.HS256, key2) // Clave secreta
        .compact();
    }

    // Método para validar el token
    public boolean validateToken(String token, String username) {
        String tokenUsername = getUsernameFromToken(token);
        return (username.equals(tokenUsername) && !isTokenExpired(token));
    }

    // Obtener el usuario del token
    public String getUsernameFromToken(String token) {
        return getClaims(token).getSubject();
    }

    // Verificar si el token expiró
    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    // Obtener datos del token
    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(key2)
                .parseClaimsJws(token)
                .getBody();
    }
}
