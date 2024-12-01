package com.example.avance2_proyfinal.controller;

import com.example.avance2_proyfinal.model.Categoria;
import com.example.avance2_proyfinal.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // Obtener la lista de todas las categorías registradas
    @GetMapping
    public List<Categoria> getAllCategorias() {
        return categoriaService.getAllCategorias();
    }

    // Obtener una categoría por su id
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable int id) {
        Categoria categoria = categoriaService.getCategoriaById(id);
        if (categoria != null) {
            return ResponseEntity.ok(categoria);
        }
        return ResponseEntity.notFound().build();
    }

    // Registrar una categoría
    @PostMapping
    public Categoria addCategoria(@RequestBody Categoria categoria) {
        return categoriaService.addCategoria(categoria);
    }

    // Actualizar una categoría por su id
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable int id, @RequestBody Categoria categoria) {
        Categoria updatedCategoria = categoriaService.updateCategoria(id, categoria);
        if (updatedCategoria != null) {
            return ResponseEntity.ok(updatedCategoria);
        }
        return ResponseEntity.notFound().build();
    }

    // Eliminar una categoría por su id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable int id) {
        categoriaService.deleteCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
