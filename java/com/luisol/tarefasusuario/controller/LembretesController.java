package com.luisol.tarefasusuario.controller;

import com.luisol.tarefasusuario.model.Lembretes;
import com.luisol.tarefasusuario.service.LembretesService;
import com.luisol.tarefasusuario.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/lembretes")
@Validated
public class LembretesController {
    @Autowired
    private LembretesService lembretesService;
    @Autowired
    private UsuarioService usuarioService;
    @GetMapping("/{id}")
    public ResponseEntity<Lembretes> findById(@PathVariable Long id){
        Lembretes obj = this.lembretesService.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<Lembretes>> findAllByUserId(@PathVariable Long userId){
        this.usuarioService.findById(userId);
        List<Lembretes> objs = this.lembretesService.findAllByUserId(userId);
        return ResponseEntity.ok().body(objs);
    }
    @PostMapping
    @Validated
    public ResponseEntity<Void> create(@Valid @RequestBody Lembretes obj){
        this.lembretesService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping
    @Validated
    public ResponseEntity<Void> update(@Valid @RequestBody Lembretes obj, @PathVariable Long id){
        obj.setId(id);
        this.lembretesService.update(obj);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.lembretesService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
