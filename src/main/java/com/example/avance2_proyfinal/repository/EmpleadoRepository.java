package com.example.avance2_proyfinal.repository;

import com.example.avance2_proyfinal.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer > {
    Optional<Empleado> findByUsername(String username);
}
