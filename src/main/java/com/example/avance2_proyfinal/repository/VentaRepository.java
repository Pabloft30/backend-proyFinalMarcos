package com.example.avance2_proyfinal.repository;
import com.example.avance2_proyfinal.model.Empleado;
import com.example.avance2_proyfinal.model.Producto;
import com.example.avance2_proyfinal.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {

    Venta findByProducto(Producto producto);

    Venta findByEmpleado(Empleado empleado);

}
