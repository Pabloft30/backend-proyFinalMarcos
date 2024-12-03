package com.example.avance2_proyfinal.controller;

import com.example.avance2_proyfinal.model.Categoria;
import com.example.avance2_proyfinal.model.Rol;
import com.example.avance2_proyfinal.service.CategoriaService;
import com.example.avance2_proyfinal.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    @Autowired
    private RolService rolService;

    // Obtener la lista de todas las categorías registradas
    @GetMapping
    public List<Rol> getAllCategorias() {
        return rolService.getAllRoles();
    }

    // Obtener una categoría por su id
    @GetMapping("/{id}")
    public ResponseEntity<Rol> getCategoriaById(@PathVariable int id) {
        Rol rol = rolService.getRolById(id);
        if (rol != null) {
            return ResponseEntity.ok(rol);
        }
        return ResponseEntity.notFound().build();
    }

}
