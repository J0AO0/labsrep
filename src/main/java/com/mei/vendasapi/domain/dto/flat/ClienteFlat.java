package com.mei.vendasapi.domain.dto.flat;

import com.mei.vendasapi.domain.Cliente;

import java.time.OffsetDateTime;

public class ClienteFlat {
    private Integer id;
    private String nome;
    private String telefone;
    private String email;
    private Boolean status;
    private String cpfoucnpj;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getCpfoucnpj() {
        return cpfoucnpj;
    }

    public void setCpfoucnpj(String cpfoucnpj) {
        this.cpfoucnpj = cpfoucnpj;
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

    public ClienteFlat(Integer id, String nome, String telefone, String email, Boolean status, String cpfoucnpj, OffsetDateTime datagravacao, String emailusuario) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.status = status;
        this.cpfoucnpj = cpfoucnpj;
        this.datagravacao = datagravacao;
        this.emailusuario = emailusuario;
    }

    public ClienteFlat(Cliente cli, String ok){
        this.id = cli.getId();
        this.nome = cli.getNome();
        this.telefone = cli.getTelefone();
        this.email = cli.getEmail();
        this.status = cli.getStatus();
        this.cpfoucnpj = cli.getCpfoucnpj();
        this.datagravacao = cli.getLogs().getDatagravacao();
        this.emailusuario = cli.getLogs().getEmailUsuario();
    }
}
