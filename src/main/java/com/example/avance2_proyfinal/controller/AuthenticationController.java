package com.example.avance2_proyfinal.controller;

import com.example.avance2_proyfinal.model.Empleado;
import com.example.avance2_proyfinal.service.EmpleadoService;
import com.example.avance2_proyfinal.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private EmpleadoService empleadoService;

    // Endpoint para autenticación de empleados
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestParam String nom_usuario, @RequestParam String password) {
        try {
            // Autenticación utilizando el AuthenticationManager
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(nom_usuario, password)
            );

            // Obtención de los detalles del empleado autenticado
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Generar el token JWT con el nombre de usuario y roles
            String token = jwtUtil.generateToken(
                    userDetails.getUsername(), // El nombre de usuario (nom_usuario)
                    userDetails.getAuthorities().iterator().next().getAuthority() // Asumiendo que el primer rol es el más relevante
            );

            // Buscar al empleado en la base de datos, usando el ID (asumido que nom_usuario es el ID del empleado)
            Empleado empleado = empleadoService.getEmpleadoById(Integer.parseInt(userDetails.getUsername()));
            if (empleado == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Empleado no encontrado");
            }

            // Crear la respuesta con el token y los detalles del empleado
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("empleado", new HashMap<String, Object>() {{
                put("id", empleado.getId());
                put("nombre", empleado.getNombre());
                put("apellido", empleado.getApellido());
                put("correo", empleado.getCorreo());
                put("telefono", empleado.getTelefono());
                put("turno", empleado.getTurno());
                put("edad", empleado.getEdad());
                put("genero", empleado.getGenero());
                put("direccion", empleado.getDireccion());
                put("roles", empleado.getRoles()); // roles del empleado, asumiendo que los roles están definidos en la entidad Empleado
            }});

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Credenciales inválidas");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error de autenticación: " + e.getMessage());
        }
    }
}
