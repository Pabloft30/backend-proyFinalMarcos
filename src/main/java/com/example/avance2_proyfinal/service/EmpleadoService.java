package com.example.avance2_proyfinal.service;

import com.example.avance2_proyfinal.model.Empleado;
import com.example.avance2_proyfinal.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    public Empleado getEmpleadoById(int id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    public Empleado addEmpleado(Empleado empleado) {
        empleado.setPassword(passwordEncoder.encode(empleado.getPassword()));
        return empleadoRepository.save(empleado);
    }

    public Empleado updateEmpleado(int id, Empleado empleado) {
        Empleado emp = empleadoRepository.findById(id).orElse(null);
        if (emp != null) {

            emp.setNombre(empleado.getNombre());
            emp.setApellido(empleado.getApellido());
            emp.setTurno(empleado.getTurno());
            emp.setTelefono(empleado.getTelefono());
            emp.setCorreo(empleado.getCorreo());
            emp.setEdad(empleado.getEdad());
            emp.setGenero(empleado.getGenero());
            emp.setDireccion(empleado.getDireccion());
            emp.setUsername(empleado.getUsername()); // Campo corregido aquí
            if (empleado.getPassword() != null && !empleado.getPassword().isEmpty()) {
                emp.setPassword(passwordEncoder.encode(empleado.getPassword()));
            }
            emp.setRoles(empleado.getRoles());

            return empleadoRepository.save(emp);
        }
        return null; // Manejar mejor con una excepción en un caso real
    }

    public void deleteEmpleado(int id) {
        empleadoRepository.deleteById(id);
    }

    public Empleado findByUsername(String username) {
        return empleadoRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

    }
}
