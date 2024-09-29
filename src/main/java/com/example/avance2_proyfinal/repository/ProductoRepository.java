package com.example.avance2_proyfinal.repository;

import com.example.avance2_proyfinal.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository  extends JpaRepository<Producto, Integer> {
}
