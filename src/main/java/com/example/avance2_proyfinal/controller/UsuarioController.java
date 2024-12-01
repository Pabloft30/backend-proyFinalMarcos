package com.example.avance2_proyfinal.controller;

import com.example.avance2_proyfinal.model.Usuario;
import com.example.avance2_proyfinal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Usuario usuario) {
        Usuario user = usuarioService.authenticate(usuario.getNombre(), usuario.getPassword());

        if (user != null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Login exitoso");
            // Aquí podrías agregar un token JWT o más información si es necesario
            return ResponseEntity.ok(response); // Devuelve un objeto JSON
        }

        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", "Credenciales inválidas");
        return ResponseEntity.status(401).body(errorResponse); // Devuelve un objeto JSON de error
    }



    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable int id) {
        Usuario usuario = usuarioService.getUsuarioById(id);
        if (usuario == null) {
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Usuario addUsuario(@RequestBody Usuario usuario) {
        return usuarioService.addUsuario(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable int id, @RequestBody Usuario usuario) {
        Usuario updatedUsuario = usuarioService.updateUsuario(id, usuario);
        if(updatedUsuario!=null) {
            return ResponseEntity.ok(updatedUsuario);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deleteUsuario(@PathVariable int id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }

}
