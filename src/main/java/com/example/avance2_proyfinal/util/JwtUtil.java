package com.example.avance2_proyfinal.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Método que genera el token, ahora el parámetro "rol" es un String.
    public String generateToken(String nom_usuario, String rol) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("rol", rol); // Almacenas el nombre del rol como un String en el claim
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(nom_usuario) // "nom_usuario" es el nombre de usuario del empleado
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // Expiración en 1 hora
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Método para extraer todos los claims del token
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Método para extraer el "nom_usuario" del token
    public String extractNomUsuario(String token) {
        return extractAllClaims(token).getSubject(); // Devuelve el nombre de usuario del token
    }

    // Método para extraer el rol del token
    public String extractRol(String token) {
        return (String) extractAllClaims(token).get("rol"); // Devuelve el rol como String
    }

    // Verificar si el token ha expirado
    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    // Validar si el token es válido comparando el nombre de usuario y la expiración
    public boolean validateToken(String token, String nom_usuario) {
        String tokenNomUsuario = extractNomUsuario(token);
        return (tokenNomUsuario.equals(nom_usuario) && !isTokenExpired(token));
    }
}
