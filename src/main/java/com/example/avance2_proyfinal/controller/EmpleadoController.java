package com.example.avance2_proyfinal.controller;

import com.example.avance2_proyfinal.service.EmpleadoService;
import com.example.avance2_proyfinal.model.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/empleados")
@PreAuthorize("hasRole('ADMIN')")  // Esto se aplica a todos los métodos de esta clase
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public List<Empleado> getAllEmpleados() {
        return empleadoService.getAllEmpleados();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> getEmpleadoById(@PathVariable int id) {
        Empleado empleado = empleadoService.getEmpleadoById(id);
        if (empleado != null) {
            return ResponseEntity.ok(empleado);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Empleado addEmpleado(@RequestBody Empleado empleado) {
        return empleadoService.addEmpleado(empleado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> updateEmpleado(@PathVariable int id, @RequestBody Empleado empleado) {
        Empleado updatedEmpleado = empleadoService.updateEmpleado(id, empleado);
        if (updatedEmpleado != null) {
            return ResponseEntity.ok(updatedEmpleado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Empleado> deleteEmpleado(@PathVariable int id) {
        empleadoService.deleteEmpleado(id);
        return ResponseEntity.noContent().build();
    }
}
