package com.mei.vendasapi.domain.dto;

import com.mei.vendasapi.domain.Cliente;
import com.mei.vendasapi.domain.TipoPedido;

import java.math.BigDecimal;

public class PedidoNewDTO {
    private Integer id;
    private Cliente cliente;
    private TipoPedido tipoPedido;
    private String tipoFrete;
    private String tipoVenda;
    private BigDecimal valorFrete;
    private String condPagamento;
    private BigDecimal comissao;

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

    public String getTipoFrete() {
        return tipoFrete;
    }

    public void setTipoFrete(String tipoFrete) {
        this.tipoFrete = tipoFrete;
    }

    public String getTipoVenda() {
        return tipoVenda;
    }

    public void setTipoVenda(String tipoVenda) {
        this.tipoVenda = tipoVenda;
    }

    public BigDecimal getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(BigDecimal valorFrete) {
        this.valorFrete = valorFrete;
    }

    public String getCondPagamento() {
        return condPagamento;
    }

    public void setCondPagamento(String condPagamento) {
        this.condPagamento = condPagamento;
    }

    public BigDecimal getComissao() {
        return comissao;
    }

    public void setComissao(BigDecimal comissao) {
        this.comissao = comissao;
    }

    public PedidoNewDTO(Integer id, Cliente cliente, TipoPedido tipoPedido, String tipoFrete, String tipoVenda,
                        BigDecimal valorFrete, String condPagamento, BigDecimal comissao) {
        this.id = id;
        this.cliente = cliente;
        this.tipoPedido = tipoPedido;
        this.tipoFrete = tipoFrete;
        this.tipoVenda = tipoVenda;
        this.valorFrete = valorFrete;
        this.condPagamento = condPagamento;
        this.comissao = comissao;
    }

    public PedidoNewDTO() {
    }
}
