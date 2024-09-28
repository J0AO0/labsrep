package com.mei.vendasapi.domain.dto;

import com.mei.vendasapi.validation.cliente.ClienteUpdate;

@ClienteUpdate
public class ClienteDTO {
    private Integer id;
    private String nome;
    private String telefone;
    private String email;
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

    public String getCpfoucnpj() {
        return cpfoucnpj;
    }

    public void setCpfoucnpj(String cpfoucnpj) {
        this.cpfoucnpj = cpfoucnpj;
    }

    public ClienteDTO(Integer id, String nome, String telefone, String email, String cpfoucnpj) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.cpfoucnpj = cpfoucnpj;
    }
}
