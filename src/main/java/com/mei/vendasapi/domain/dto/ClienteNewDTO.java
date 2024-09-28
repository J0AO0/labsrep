package com.mei.vendasapi.domain.dto;

import com.mei.vendasapi.validation.cliente.ClienteInsert;
import com.mei.vendasapi.validation.cliente.ClienteUpdate;

@ClienteInsert
public class ClienteNewDTO {
    private Integer id;
    private String nome;
    private String telefone;
    private String email;
    private Boolean status = Boolean.TRUE;
    private String cpfoucnpj;


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

    public ClienteNewDTO(Integer id, String nome, String telefone, String email, Boolean status, String cpfoucnpj) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.status = status;
        this.cpfoucnpj = cpfoucnpj;
    }
}
