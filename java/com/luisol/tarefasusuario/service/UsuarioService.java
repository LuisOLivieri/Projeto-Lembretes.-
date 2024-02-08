package com.luisol.tarefasusuario.service;

import com.luisol.tarefasusuario.model.Usuario;
import com.luisol.tarefasusuario.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
public class UsuarioService {
    @Autowired //Injeção de dependencia.
    private UsuarioRepository usuarioRepository; //Interface da JPA que é auto injetada.

    // CRUD Básico. (CREATE, READ(FIND), UPDATE, DELETE

    public Usuario findById(Long id){ //Método publico, que retorna um tipo Usuario, recebendo como param id.
        Optional<Usuario> usuario = this.usuarioRepository.findById(id); //Optional porque pode ou não receber algo.
        return usuario.orElseThrow(() -> new RuntimeException("Usuário não encontrado! ID: " + id + ", Tipo: " + Usuario.class.getName()));
    }
    @Transactional
    public Usuario create(Usuario obj){  //Criar usuário (obj) representa o novo usuario criado.
        obj.setId(null); // O id desse novo usuário é null, pois isso faz com que ele seja atribuido no BD e seja unico.
        obj = this.usuarioRepository.save(obj); //Salva o obj criado no BD. Retorna o id do obj criado.
        return obj; //Retorna o obj criado no banco de dados.
    }
    public Usuario update(Usuario obj){
        Usuario newObj = findById(obj.getId());
        newObj.setUsername(obj.getUsername());
        return this.usuarioRepository.save(newObj);
    }
    public void delete(Long id){
        findById(id);
        try {
            this.usuarioRepository.deleteById(id);
        } catch (Exception e){
            throw new RuntimeException("Não é possível excluir pois há entidades relacionadas!");
        }
    }

}
