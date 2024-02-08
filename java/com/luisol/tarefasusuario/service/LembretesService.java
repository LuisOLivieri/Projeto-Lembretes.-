package com.luisol.tarefasusuario.service;

import com.luisol.tarefasusuario.model.Lembretes;
import com.luisol.tarefasusuario.model.Usuario;
import com.luisol.tarefasusuario.repositories.LembretesRepository;
import com.luisol.tarefasusuario.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class LembretesService {
    @Autowired
    private LembretesRepository lembretesRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Lembretes findById(Long id){
        Optional<Lembretes> lembretes = this.lembretesRepository.findById(id);
        return lembretes.orElseThrow(()-> new RuntimeException("Tarefa não encontrada! ID: " + id + ", Tipo: " + Lembretes.class.getName()));
    }

    public List<Lembretes> findAllByUserId(Long userId){
        List<Lembretes> lembretes = this.lembretesRepository.findByUsuario_Id(userId);
        return lembretes;
    }
    @Transactional
    public Lembretes create(Lembretes obj){
        Optional<Usuario> usuario = this.usuarioRepository.findById(obj.getUsuario().getId());
        obj.setId(null);
        obj.setUsuario(usuario.orElseThrow());
        obj = this.lembretesRepository.save(obj);
        return obj;
    }
    @Transactional
    public Lembretes update(Lembretes obj){
        Lembretes newObj = findById(obj.getId());
        newObj.setDescricao(obj.getDescricao());
        return this.lembretesRepository.save(newObj);
    }
    public void delete(Long id){
        findById(id);
            try {
                this.lembretesRepository.deleteById(id);
            } catch (Exception e){
                throw new RuntimeException("Não é possível excluir pois há entidades relacionadas!");
        }
    }


}
