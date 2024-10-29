package com.example.avance2_proyfinal.repository;

import com.example.avance2_proyfinal.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer > {
    Empleado findByNombre(String nombre);
}
