package com.example.avance2_proyfinal.controller;

import com.example.avance2_proyfinal.service.RegistroVentaService;
import com.example.avance2_proyfinal.model.RegistroVenta;
import com.example.avance2_proyfinal.repository.RegistroVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registros")
public class RegistroVentaController {

    @Autowired
    private RegistroVentaService registroVentaService;

    @GetMapping
    public List<RegistroVenta> getAllRegistros(){
        return registroVentaService.getAllRegistros();
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<RegistroVenta> getRegistroById(@PathVariable int id){
        RegistroVenta registroVenta = registroVentaService.getRegistroVentaById(id);
        if(registroVenta == null){
            return ResponseEntity.ok(registroVenta);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public RegistroVenta addRegistro(@RequestBody RegistroVenta registroVenta){
        return registroVentaService.addRegistroVenta(registroVenta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistroVenta> updateRegistro(@PathVariable int id, @RequestBody RegistroVenta registroVenta){
        RegistroVenta registroVentaUpdated = registroVentaService.updateRegistroVenta(id, registroVenta);
        if(registroVentaUpdated != null){
            return ResponseEntity.ok(registroVentaUpdated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RegistroVenta> deleteRegistro(@PathVariable int id){
        registroVentaService.deleteRegistroVenta(id);
        return ResponseEntity.noContent().build();
    }

}
