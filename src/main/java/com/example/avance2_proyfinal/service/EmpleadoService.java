package com.example.avance2_proyfinal.service;

import com.example.avance2_proyfinal.model.Empleado;
import com.example.avance2_proyfinal.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    public Empleado getEmpleadoById(int id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    public Empleado addEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public Empleado updateEmpleado(int id, Empleado empleado) {
        Optional<Empleado> existingEmpleado = empleadoRepository.findById(id);
        if (existingEmpleado.isPresent()) {
            Empleado emp = existingEmpleado.get();

            // Actualizamos los campos de Empleado
            emp.setNombre(empleado.getNombre());
            emp.setApellido(empleado.getApellido());
            emp.setTurno(empleado.getTurno());
            emp.setTelefono(empleado.getTelefono());
            emp.setCorreo(empleado.getCorreo());
            emp.setEdad(empleado.getEdad());
            emp.setGenero(empleado.getGenero());
            emp.setDireccion(empleado.getDireccion());
            emp.setNombreUsuario(empleado.getNombreUsuario()); // Campo corregido aquí
            emp.setPassword(empleado.getPassword());
            emp.setRoles(empleado.getRoles());

            return empleadoRepository.save(emp);
        }
        return null; // Manejar mejor con una excepción en un caso real
    }

    public void deleteEmpleado(int id) {
        empleadoRepository.deleteById(id);
    }
}
