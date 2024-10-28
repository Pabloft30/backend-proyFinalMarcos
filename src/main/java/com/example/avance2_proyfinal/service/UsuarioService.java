package com.example.avance2_proyfinal.service;

import com.example.avance2_proyfinal.model.Usuario;
import com.example.avance2_proyfinal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(int id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario addUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(int id, Usuario usuario) {
        Usuario usu = usuarioRepository.findById(id).orElse(null);
        if (usu != null) {
            usu.setNombre(usuario.getNombre());
            usu.setContraseña(usuario.getContraseña());
            return usuarioRepository.save(usu);
        }
        return null;
    }

    public void deleteUsuario(int id) {
        usuarioRepository.deleteById(id);
    }


}
