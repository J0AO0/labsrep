package com.mei.vendasapi.domain.dto;

import com.mei.vendasapi.domain.Cliente;
import com.mei.vendasapi.domain.TipoPedido;

import javax.persistence.ManyToOne;

public class PedidoDTO {
    private Integer id;
    private TipoPedido tipoPedido;
    private Cliente cliente;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public TipoPedido getTipoPedido() {
        return tipoPedido;
    }

    public void setTipoPedido(TipoPedido tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    public PedidoDTO(Integer id, Cliente cliente, TipoPedido tipoPedido) {
        this.id = id;
        this.cliente = cliente;
        this.tipoPedido = tipoPedido;
    }

    public PedidoDTO() {
    }
}
