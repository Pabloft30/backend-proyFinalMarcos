package com.example.avance2_proyfinal.controller;

import com.example.avance2_proyfinal.model.Producto;
import com.example.avance2_proyfinal.model.Venta;
import com.example.avance2_proyfinal.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // Obtener todos los productos
    @GetMapping
    public ResponseEntity<List<Producto>> getAllProductos() {
        List<Producto> productos = productoService.getAllProductos();
        return ResponseEntity.ok(productos);
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable int id) {
        Optional<Producto> producto = productoService.getProductoById(id);
        return producto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Registrar un nuevo producto
    @PostMapping
    public ResponseEntity<Producto> addProducto(@RequestBody Producto producto) {
        Producto newProducto = productoService.addProducto(producto);
        return ResponseEntity.ok(newProducto);
    }

    // Actualizar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable int id, @RequestBody Producto productoDetails) {
        Producto updatedProducto = productoService.updateProducto(id, productoDetails);
        return ResponseEntity.ok(updatedProducto);
    }

    // Eliminar un producto por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable int id) {
        productoService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }
}
