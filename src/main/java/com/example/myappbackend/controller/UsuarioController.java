package com.example.myappbackend.controller;

import com.example.myappbackend.DAO.UsuarioDAO;
import com.example.myappbackend.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1")
public class UsuarioController {
    @Autowired
    private UsuarioDAO usuarioDAO;

    @GetMapping("/usuarios")
    public List<Usuario> getUsuarios() {
        return usuarioDAO.findAll();
    }

    @PostMapping("/usuarios")
    public Usuario registrarUsuario(@RequestBody Usuario usuario) {
        return usuarioDAO.save(usuario);
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        try {
            Usuario cliente = usuarioDAO.findById(id)
                    .orElseThrow(() -> new Exception("El cliente con ese ID no existe : " + id));

            return ResponseEntity.ok(cliente);
        }catch (Exception e){
            return ResponseEntity.of(Optional.empty());
        }
    }
}
