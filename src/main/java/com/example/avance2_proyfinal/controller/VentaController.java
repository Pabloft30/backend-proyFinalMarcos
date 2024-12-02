package com.example.avance2_proyfinal.controller;

import com.example.avance2_proyfinal.model.Producto;
import com.example.avance2_proyfinal.model.Venta;
import com.example.avance2_proyfinal.service.ProductoService;
import com.example.avance2_proyfinal.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService ventaService;
    private final ProductoService productoService;

    @Autowired
    public VentaController(VentaService ventaService, ProductoService productoService) {
        this.ventaService = ventaService;
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<List<Venta>> getAllVentas() {
        List<Venta> ventas = ventaService.getAllVentas();
        return ResponseEntity.ok(ventas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> getVentaById(@PathVariable int id) {
        Optional<Venta> venta = ventaService.getVentaById(id);
        return venta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> addVenta(@RequestBody Venta venta) {
        // Verificar existencia del producto
        Optional<Producto> productoOptional = productoService.getProductoById(venta.getProducto().getId());
        if (productoOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("El producto no existe.");
        }

        Producto producto = productoOptional.get();

        // Verificar si hay stock suficiente
        if (producto.getCantidad() < venta.getCantidad()) {
            return ResponseEntity.badRequest().body("Stock insuficiente para realizar la venta.");
        }

        // Descontar el stock del producto utilizando actualizarStock
        productoService.actualizarStock(producto.getId(), venta.getCantidad());

        // Registrar la venta
        Venta newVenta = ventaService.addVenta(venta);
        return ResponseEntity.ok(newVenta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venta> updateVenta(@PathVariable int id, @RequestBody Venta ventaDetails) {
        Venta updatedVenta = ventaService.updateVenta(id, ventaDetails);
        return ResponseEntity.ok(updatedVenta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenta(@PathVariable int id) {
        ventaService.deleteVenta(id);
        return ResponseEntity.noContent().build();
    }
}
