package com.example.avance2_proyfinal.service;

import com.example.avance2_proyfinal.model.Empleado;
import com.example.avance2_proyfinal.model.Rol;
import com.example.avance2_proyfinal.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomEmpleadoDetailsService implements UserDetailsService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public UserDetails loadUserByUsername(String nom_usuario) throws UsernameNotFoundException {
        // Buscar al empleado por el nombre de usuario (nom_usuario)
        Empleado empleado = empleadoRepository.findByNom_usuario(nom_usuario)
                .orElseThrow(() -> new UsernameNotFoundException("Empleado no encontrado: " + nom_usuario));

        // Obtener los roles asociados al empleado y mapearlos a SimpleGrantedAuthority
        var authorities = empleado.getRoles().stream()
                .map(Rol::getNombre) // Obtener el nombre del rol de cada Rol asociado al empleado
                .filter(roleName -> roleName.equals("ROL_ADMIN") || roleName.equals("ROL_VENDEDOR")) // Filtrar solo los roles "ROL_ADMIN" y "ROL_VENDEDOR"
                .map(roleName -> new SimpleGrantedAuthority(roleName)) // Crear SimpleGrantedAuthority con el nombre del rol
                .collect(Collectors.toList()); // Convertir a lista

        // Devolver el objeto UserDetails con los roles y demás detalles
        return new org.springframework.security.core.userdetails.User(
                empleado.getNom_usuario(), // 'nom_usuario' es el nombre de usuario
                empleado.getPassword(), // 'password' es la contraseña
                authorities // Autoridades basadas en los roles
        );
    }
}
