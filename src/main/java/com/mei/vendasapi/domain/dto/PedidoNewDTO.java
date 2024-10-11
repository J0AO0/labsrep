package com.mei.vendasapi.domain.dto;

import com.mei.vendasapi.domain.*;

import java.math.BigDecimal;

public class PedidoNewDTO {
    private Integer id;
    private Cliente cliente;
    private TipoPedido tipoPedido;
    private TipoFrete tipoFrete;
    private FormaPagamento formaPagamento;
    private BigDecimal valorFrete;
    private CondPagamento condPagamento;
    private BigDecimal comissao;
    private String statusPedido;

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

    public String getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(String statusPedido) {
        this.statusPedido = statusPedido;
    }

    public BigDecimal getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(BigDecimal valorFrete) {
        this.valorFrete = valorFrete;
    }

    public CondPagamento getCondPagamento() {
        return condPagamento;
    }

    public void setCondPagamento(CondPagamento condPagamento) {
        this.condPagamento = condPagamento;
    }

    public BigDecimal getComissao() {
        return comissao;
    }

    public void setComissao(BigDecimal comissao) {
        this.comissao = comissao;
    }

    public TipoFrete getTipoFrete() {
        return tipoFrete;
    }

    public void setTipoFrete(TipoFrete tipoFrete) {
        this.tipoFrete = tipoFrete;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public PedidoNewDTO(Integer id, Cliente cliente, TipoPedido tipoPedido, TipoFrete tipoFrete, FormaPagamento formaPagamento,
                        BigDecimal valorFrete, CondPagamento condPagamento, BigDecimal comissao, String statusPedido) {
        this.id = id;
        this.cliente = cliente;
        this.tipoPedido = tipoPedido;
        this.tipoFrete = tipoFrete;
        this.formaPagamento = formaPagamento;
        this.valorFrete = valorFrete;
        this.condPagamento = condPagamento;
        this.comissao = comissao;
        this.statusPedido = statusPedido;
    }

    public PedidoNewDTO() {
    }
}
