package com.example.avance2_proyfinal.controller;

import com.example.avance2_proyfinal.model.Venta;
import com.example.avance2_proyfinal.model.Producto;
import com.example.avance2_proyfinal.model.Empleado;
import com.example.avance2_proyfinal.repository.*;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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
    public ResponseEntity<Venta> getVentaById(@PathVariable (value = "id") int ventaId){
        Venta venta = ventaRepository.findById(ventaId)
                .orElse(null);
        if(venta == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(venta);
    }

    @PostMapping
    public ResponseEntity<Venta> createVenta(@RequestBody Venta venta) {
        try{
            Producto prod = productoRepository.findById(venta.getProducto().getId())
                    .orElseThrow(() -> new ConfigDataResourceNotFoundException("Producto no encontrado con id: " + venta.getProducto().getId()));

            Empleado emp = empleadoRepository.findById(venta.getEmpleado().getId())
                    .orElseThrow(() ->) new ConfigDataResourceNotFoundException("Empleado no encontrado con id: " + venta.getEmpleado().getId());

            Venta venta1 = new Venta();
            venta.setProducto(prod);
            venta.setEmpleado(emp);
            venta.setFechaVenta(venta.getFechaVenta());
            venta.setMetodoPago(venta.getMetodoPago());

            Venta savedVenta = ventaRepository.save(venta1);
            return ResponseEntity.ok(savedVenta);
        } catch (ConfigDataResourceNotFoundException e){
            return ResponseEntity.badRequest().body(null);
        }hhhhhhh
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venta> updateCita(@PathVariable(value = "id") int ventaId,
                                           @RequestBody Venta venta) {
        try {
            Venta venta1 = ventaRepository.findById(ventaId)
                    .orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada con id: " + ventaId));

            Producto prod = productoRepository.findById(venta.getProducto().getId())
                    .orElseThrow(() -> new ConfigDataResourceNotFoundException("Producto no encontrado con id: " + venta.getProducto().getId()));

            Empleado emp = empleadoRepository.findById(venta.getEmpleado().getId())
                    .orElseThrow(() -> new ConfigDataResourceNotFoundException("Empleado no encontrado con id: " + venta.getProducto().getId()));

            venta.setProducto(prod);
            venta.setEmpleado(emp);
            venta.setFechaVenta(venta.getFechaVenta());
            venta.setMetodoPago(venta.getMetodoPago());

                Venta updatedVenta = ventaRepository.save(venta);
            return ResponseEntity.ok(updatedVenta);
        } catch (ConfigDataResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenta(@PathVariable(value = "id") int ventaId) {
        try {
            Venta venta = ventaRepository.findById(ventaId)
                    .orElseThrow(() -> new ConfigDataResourceNotFoundException("Venta no encontrada con id: " + ventaId));

            ventaRepository.delete(venta);
            return ResponseEntity.noContent().build();  // Devuelve 204 No Content
        } catch (ConfigDataResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

