package com.mei.vendasapi.domain.dto.flat;

import com.mei.vendasapi.domain.Categoria;

import java.time.OffsetDateTime;

public class CategoriaFlat {
    private Integer id;
    private String nome;
    private Boolean status;
    private OffsetDateTime datagravacao;
    private String emailusuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public OffsetDateTime getDatagravacao() {
        return datagravacao;
    }

    public void setDatagravacao(OffsetDateTime datagravacao) {
        this.datagravacao = datagravacao;
    }

    public String getEmailusuario() {
        return emailusuario;
    }

    public void setEmailusuario(String emailusuario) {
        this.emailusuario = emailusuario;
    }

    public CategoriaFlat(Integer id, String nome, Boolean status, OffsetDateTime datagravacao, String emailusuario) {
        this.id = id;
        this.nome = nome;
        this.status = status;
        this.datagravacao = datagravacao;
        this.emailusuario = emailusuario;
    }

    public CategoriaFlat(Categoria cat, String ok) {
        this.id = cat.getId();
        this.nome = cat.getNome();
        this.status = cat.getStatus();
        this.datagravacao = cat.getLogs().getDatagravacao();
        this.emailusuario = cat.getLogs().getEmailUsuario();
    }

    public CategoriaFlat(Categoria cat) {
        this.id = cat.getId();
        this.nome = cat.getNome();
        this.status = cat.getStatus();
        this.datagravacao = cat.getLogs().getDatagravacao();
        this.emailusuario = cat.getLogs().getEmailUsuario();
    }

}
