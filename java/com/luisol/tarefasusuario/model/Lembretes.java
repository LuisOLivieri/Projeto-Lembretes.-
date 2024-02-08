package com.luisol.tarefasusuario.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity //(Entidade persistente)Anotação para que os objetos da classe sejam armazenados em um banco de dados.
@Table(name = Lembretes.TABLE_NAME) //Nome da tabela.
public class Lembretes {
    public static final String TABLE_NAME = "lembretes"; //(Const)Evita duplicação, facilidade na manutenção, legível.

    @Id //Chave primária de um entidade.
    @GeneratedValue(strategy = GenerationType.AUTO) //Valores da chave primária gerados automáticamente no BD.
    @Column(name = "id", unique = true) //Indica que será uma coluna, nome(id), unique(valores únicos nas tabelas).
    private Long id;
    @Column(name = "descricao", length = 255, nullable = false) //Nome, tamanho, e não pode ser nulo.
    @NotNull //Não pode ser nulo.
    @NotEmpty //Não pode estar vazio.
    @Size(min = 6, max = 255) //Mínimo e o máximo de letras ou palavras.
    private String descricao;
    @ManyToOne //(Muitos(lembrestes) para um(usuário).
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario; //A classe usuário irá ter lembretes, por isso ela está instanciada em Lembretes.

    public Lembretes(){} //Construtor sem argumentos.

    public Lembretes(Long id, String descricao, Usuario usuario) { //Construtor com argumentos.
        this.id = id;
        this.descricao = descricao;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lembretes lembretes = (Lembretes) o;
        return Objects.equals(id, lembretes.id) && Objects.equals(descricao, lembretes.descricao) && Objects.equals(usuario, lembretes.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricao, usuario);
    }
}
