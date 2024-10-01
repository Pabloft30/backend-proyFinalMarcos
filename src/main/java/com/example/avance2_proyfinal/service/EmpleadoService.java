package com.example.avance2_proyfinal.service;

import com.example.avance2_proyfinal.model.Empleado;
import com.example.avance2_proyfinal.model.Producto;
import com.example.avance2_proyfinal.repository.EmpleadoRepository;
import com.example.avance2_proyfinal.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private ProductoRepository productoRepository;

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
            return empleadoRepository.save(emp);
        }
        return null;
    }

    public void deleteEmpleado(int id) {
        empleadoRepository.deleteById(id);
    }

}
