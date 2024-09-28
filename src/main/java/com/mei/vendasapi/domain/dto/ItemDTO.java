package com.mei.vendasapi.domain.dto;

import com.mei.vendasapi.domain.ProdutoPedidoPK;

public class ItemDTO {
    public ProdutoPedidoPK id = new ProdutoPedidoPK();
    private Integer quantidade;
    private Boolean status;

    public ProdutoPedidoPK getId() {
        return id;
    }

    public void setId(ProdutoPedidoPK id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public ItemDTO(ProdutoPedidoPK id, Integer quantidade, Boolean status) {
        this.id = id;
        this.quantidade = quantidade;
        this.status = status;
    }
}
