package com.example.avance2_proyfinal.filter;

import com.example.avance2_proyfinal.service.CustomEmpleadoDetailsService;
import com.example.avance2_proyfinal.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final CustomEmpleadoDetailsService customEmpleadoDetailsService;
    private final JwtUtil jwtUtil;

    // Constructor con dependencia de CustomEmpleadoDetailsService y JwtUtil
    public JwtRequestFilter(CustomEmpleadoDetailsService customEmpleadoDetailsService, JwtUtil jwtUtil) {
        this.customEmpleadoDetailsService = customEmpleadoDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        // Obtener el encabezado de autorización de la solicitud HTTP
        final String authorizationHeader = request.getHeader("Authorization");

        String nom_usuario = null;
        String jwt = null;

        // Si el encabezado contiene el prefijo "Bearer", extraemos el token
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7); // Extrae el token
            try {
                // Intentamos extraer el nombre de usuario (nom_usuario) desde el JWT
                nom_usuario = jwtUtil.extractNomUsuario(jwt);
            } catch (Exception e) {
                // Si ocurre algún error en la extracción del usuario o token, manejamos el error
                logger.error("Error al procesar el JWT", e);
            }
        }

        // Si el nombre de usuario no es nulo y no hay autenticación previa, autenticar al empleado
        if (nom_usuario != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Cargar los detalles del empleado desde la base de datos
            UserDetails userDetails = customEmpleadoDetailsService.loadUserByUsername(nom_usuario);
            if (jwt != null && !jwtUtil.isTokenExpired(jwt)) { // Verificar si el token no ha expirado
                // Si el token no ha expirado, crear un token de autenticación con los detalles del empleado
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );
                // Establecer el contexto de seguridad con el token de autenticación
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Continuar con la cadena de filtros
        filterChain.doFilter(request, response);
    }
}
