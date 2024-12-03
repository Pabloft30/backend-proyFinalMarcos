package com.example.avance2_proyfinal.service;

import com.example.avance2_proyfinal.model.Empleado;
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Empleado empleado = empleadoRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        System.out.println("Empleado encontrado: " + empleado.getUsername());
        System.out.println("Roles asociados: " + empleado.getRoles());

        var authorities = empleado.getRoles().stream()
                .map(rol -> new SimpleGrantedAuthority("ROLE_" + rol.getNombre()))
                .collect(Collectors.toList());

        System.out.println("Roles mapeados: " + authorities);

        return new org.springframework.security.core.userdetails.User(
                empleado.getUsername(),
                empleado.getPassword(),
                authorities
        );
    }


}
