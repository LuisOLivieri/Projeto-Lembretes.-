package com.luisol.tarefasusuario.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity //(Entidade persistente)Anotação para que os objetos da classe sejam armazenados em um banco de dados.
@Table(name = Usuario.TABLE_NAME) //Nome da tabela.
public class Usuario {
    public static final String TABLE_NAME = "usuario"; //(Const)Evita duplicação, facilidade na manutenção, legível.
    @Id //Chave primária de um entidade.
    @GeneratedValue(strategy = GenerationType.AUTO) //Valores da chave primária gerados automáticamente no BD.
    @Column(name = "id", unique = true) //Indica que será uma coluna, nome(id), unique(valores únicos nas tabelas).
    private Long id;

            //Nome da coluna, tamanho do Usuario, não pode ser nulo e é unico.
    @Column(name = "username", length = 100, nullable = false, unique = true)
    @NotNull //Validações (pom.xml) não pode ser nulo.
    @NotEmpty //Validações (pom.xml) não pode estar vazio ao criar.
    @Size(min = 8, max = 40) //Validações (pom.xml) tamanho mínimo e máximo do username.
    private String username;
    @OneToMany(mappedBy = "usuario") //Um(usuario) tem muitos(lembretes).
    private List<Lembretes> lembreste = new ArrayList<>(); //Cria a lista conforme for adicionados lembretes.

    public Usuario(){} //Construtor vazio.
    public Usuario(Long id, String username) { //Construtor com argumentos.
        this.id = id;
        this.username = username;
    }
    //Getters e setters - Acessar e modificar valores.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    //Equals - comparação dos objetos.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) && Objects.equals(username, usuario.username);
    }
    //HashCode - código para identificar o objeto em uma estrutura.
    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }
}
