package com.example.avance2_proyfinal.service;

import com.example.avance2_proyfinal.model.Categoria;
import com.example.avance2_proyfinal.model.Rol;
import com.example.avance2_proyfinal.repository.CategoriaRepository;
import com.example.avance2_proyfinal.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public List<Rol> getAllRoles(){
        return rolRepository.findAll();
    }

    public Rol getRolById(int id){
        return rolRepository.findById(id).orElse(null);
    }


}
