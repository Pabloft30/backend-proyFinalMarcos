package com.example.avance2_proyfinal.controller;

import com.example.avance2_proyfinal.model.Venta;
import com.example.avance2_proyfinal.model.Producto;
import com.example.avance2_proyfinal.model.Empleado;
import com.example.avance2_proyfinal.repository.VentaRepository;
import com.example.avance2_proyfinal.repository.ProductoRepository;
import com.example.avance2_proyfinal.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//añaaaaaa

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @GetMapping
    public List<Venta> getAll() {
        return ventaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> getVentaById(@PathVariable(value = "id") int ventaId) {
        Venta venta = ventaRepository.findById(ventaId).orElse(null);
        if (venta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(venta);
    }

    @PostMapping
    public ResponseEntity<Venta> createVenta(@RequestBody Venta venta) {
        try {
            Producto prod = productoRepository.findById(venta.getProducto().getId()).orElse(null);
            Empleado emp = empleadoRepository.findById(venta.getEmpleado().getId()).orElse(null);

            if (prod == null || emp == null) {
                return ResponseEntity.badRequest().body(null);
            }

            venta.setProducto(prod);
            venta.setEmpleado(emp);
            // Asegúrate de que estos atributos existen en tu modelo de Venta
            venta.setFechaVenta(venta.getFechaVenta());
            venta.setMetodoPago(venta.getMetodoPago());

            Venta savedVenta = ventaRepository.save(venta);
            return ResponseEntity.ok(savedVenta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venta> updateVenta(@PathVariable(value = "id") int ventaId,
                                             @RequestBody Venta venta) {
        Venta existingVenta = ventaRepository.findById(ventaId).orElse(null);
        if (existingVenta == null) {
            return ResponseEntity.notFound().build();
        }

        Producto prod = productoRepository.findById(venta.getProducto().getId()).orElse(null);
        Empleado emp = empleadoRepository.findById(venta.getEmpleado().getId()).orElse(null);

        if (prod == null || emp == null) {
            return ResponseEntity.badRequest().body(null);
        }

        existingVenta.setProducto(prod);
        existingVenta.setEmpleado(emp);
        existingVenta.setFechaVenta(venta.getFechaVenta());
        existingVenta.setMetodoPago(venta.getMetodoPago());

        Venta updatedVenta = ventaRepository.save(existingVenta);
        return ResponseEntity.ok(updatedVenta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenta(@PathVariable(value = "id") int ventaId) {
        Venta venta = ventaRepository.findById(ventaId).orElse(null);
        if (venta == null) {
            return ResponseEntity.notFound().build();
        }
        ventaRepository.delete(venta);
        return ResponseEntity.noContent().build();  // Devuelve 204 No Content
    }
}