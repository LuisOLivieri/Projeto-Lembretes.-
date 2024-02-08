package com.luisol.tarefasusuario.controller;

import com.luisol.tarefasusuario.model.Usuario;
import com.luisol.tarefasusuario.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuario")
@Validated
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id){
        Usuario obj = this.usuarioService.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    @PostMapping
    @Validated
    public ResponseEntity<Void> create(@Valid @RequestBody Usuario obj){
        this.usuarioService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<Void> update(@Valid @RequestBody Usuario obj, @PathVariable Long id){
        obj.setId(id);
        obj = this.usuarioService.update(obj);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
