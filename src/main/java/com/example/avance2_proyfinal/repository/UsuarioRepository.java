package com.example.avance2_proyfinal.repository;

import com.example.avance2_proyfinal.model.Empleado;
import com.example.avance2_proyfinal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByNombre(String nombre);

    Usuario findByEmpleado(Empleado empleado);

}
