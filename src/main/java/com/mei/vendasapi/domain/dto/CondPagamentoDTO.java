package com.mei.vendasapi.domain.dto;

import com.mei.vendasapi.domain.Tenant;

import javax.persistence.ManyToOne;

public class CondPagamentoDTO {
    private Integer id;
    private String descricao;
    private Boolean status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public CondPagamentoDTO(Integer id, String descricao, Boolean status) {
        this.id = id;
        this.descricao = descricao;
        this.status = status;
    }
}
