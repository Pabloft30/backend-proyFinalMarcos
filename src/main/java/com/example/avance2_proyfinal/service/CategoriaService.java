package com.example.avance2_proyfinal.service;

import com.example.avance2_proyfinal.model.Categoria;
import com.example.avance2_proyfinal.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> getAllCategorias(){
        return categoriaRepository.findAll();
    }

    public Categoria getCategoriaById(int id){
        return categoriaRepository.findById(id).orElse(null);
    }

    public Categoria addCategoria(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public Categoria updateCategoria(int id, Categoria categoria){
        Categoria cate = categoriaRepository.findById(id).orElse(null);
        if(cate != null){
            cate.setId(categoria.getId());
            cate.setNombre(categoria.getNombre());
            return categoriaRepository.save(cate);
        }
        return null;
    }

    public void deleteCategoria(int id){
        categoriaRepository.deleteById(id);
    }

}
