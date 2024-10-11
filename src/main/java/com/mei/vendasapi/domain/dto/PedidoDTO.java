package com.mei.vendasapi.domain.dto;

import com.mei.vendasapi.domain.*;

import javax.persistence.ManyToOne;
import java.math.BigDecimal;

public class PedidoDTO {
    private Integer id;
    private TipoPedido tipoPedido;
    private Cliente cliente;
    private TipoFrete tipoFrete;
    private FormaPagamento formaPagamento;
    private BigDecimal valorFrete;
    private CondPagamento condPagamento;
    private BigDecimal comissao;
    private String statusPedido;


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

    public String getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(String statusPedido) {
        this.statusPedido = statusPedido;
    }

    public PedidoDTO(Integer id, TipoPedido tipoPedido, Cliente cliente, TipoFrete tipoFrete, FormaPagamento formaPagamento,
                     BigDecimal valorFrete, CondPagamento condPagamento, BigDecimal comissao) {
        this.id = id;
        this.tipoPedido = tipoPedido;
        this.cliente = cliente;
        this.tipoFrete = tipoFrete;
        this.formaPagamento = formaPagamento;
        this.valorFrete = valorFrete;
        this.condPagamento = condPagamento;
        this.comissao = comissao;
    }

    public PedidoDTO() {
    }
}
