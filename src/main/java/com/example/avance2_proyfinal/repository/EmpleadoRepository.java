package com.example.avance2_proyfinal.repository;

import com.example.avance2_proyfinal.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer > {

    Optional<Empleado> findByNom_usuario(String nom_usuario);

}
