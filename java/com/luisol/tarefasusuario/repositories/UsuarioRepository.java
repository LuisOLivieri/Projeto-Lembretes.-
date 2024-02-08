package com.luisol.tarefasusuario.repositories;

import com.luisol.tarefasusuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
