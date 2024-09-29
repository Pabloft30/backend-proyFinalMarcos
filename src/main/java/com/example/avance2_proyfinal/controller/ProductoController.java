package com.example.avance2_proyfinal.controller;


import com.example.avance2_proyfinal.service.ProductoService;
import com.example.avance2_proyfinal.model.Producto;
import com.example.avance2_proyfinal.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    //Obtener la lista de todos los productos registrados
    @GetMapping
    public List<Producto> getAllProductos() {
        return productoService.getAllProductos();
    }

    //Obtener un producto por su id
    @GetMapping({"/{id_producto}"})
    public ResponseEntity<Producto> getProductoById(@PathVariable int id_producto) {
        Producto producto = productoService.getProductoById(id_producto);
        if (producto == null) {
            return ResponseEntity.ok(producto);
        }
        return ResponseEntity.notFound().build();
    }

    //Registrar un producto
    @PostMapping
    public Producto addProducto(@RequestBody Producto producto) {
        return productoService.addProducto(producto);
    }

    //Actualizar un producto por su id
    @PutMapping("/{id_producto}")
    public ResponseEntity<Producto> updateProducto(@PathVariable int id_producto, @RequestBody Producto producto) {
        Producto updatedProducto = productoService.updateProducto(id_producto, producto);
        if (updatedProducto!=null){
            return ResponseEntity.ok(updatedProducto);
        }
        return ResponseEntity.notFound().build();
    }

    //Eliminar un producto por su id
    @DeleteMapping("/{id_producto}")
    public ResponseEntity<Producto> deleteProducto(@PathVariable int id_producto) {
        productoService.deleteProducto(id_producto);
        return ResponseEntity.noContent().build();
    }

}
