package com.luisol.tarefasusuario.repositories;

import com.luisol.tarefasusuario.model.Lembretes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LembretesRepository extends JpaRepository<Lembretes, Long> {
    List<Lembretes> findByUsuario_Id(Long id);
}
