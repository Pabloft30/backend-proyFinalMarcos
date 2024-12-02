package com.example.avance2_proyfinal.service;

import com.example.avance2_proyfinal.model.Usuario;
import com.example.avance2_proyfinal.model.Empleado;
import com.example.avance2_proyfinal.repository.UsuarioRepository;
import com.example.avance2_proyfinal.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(int id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario addUsuario(Usuario usuario) {
        Empleado empleado = empleadoRepository.findById(usuario.getEmpleado().getId())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        usuario.setEmpleado(empleado);
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(int id,Usuario usuario) {
        Usuario usu=usuarioRepository.findById(id).orElse(null);
        if (usu != null) {
            Empleado empleado = empleadoRepository.findById(usuario.getEmpleado().getId())
                    .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
            usu.setNombre(usuario.getNombre());
            usu.setPassword(usuario.getPassword());
            usu.setEmpleado(empleado);
            return usuarioRepository.save(usu);
        }
        return null;
    }

    public void deleteUsuario(int id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario authenticate(String nombre, String password) {
        Usuario usuario = usuarioRepository.findByNombre(nombre); // Cambia aquí también
        if (usuario != null && usuario.getPassword().equals(password)) {
            return usuario; // Aquí deberías implementar el hashing más adelante
        }
        return null;
    }



}
