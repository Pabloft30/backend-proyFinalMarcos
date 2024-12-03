package com.example.avance2_proyfinal.repository;

import com.example.avance2_proyfinal.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {

    Optional<Rol> findByName(String name);
}
