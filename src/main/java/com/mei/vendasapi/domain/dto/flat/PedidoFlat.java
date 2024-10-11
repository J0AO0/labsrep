package com.mei.vendasapi.domain.dto.flat;

import com.mei.vendasapi.domain.*;

import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class PedidoFlat {
    private Integer id;
    private Cliente cliente;
    private TipoFrete tipoFrete;
    private FormaPagamento formaPagamento;
    private BigDecimal valorFrete;
    private CondPagamento condPagamento;
    private BigDecimal comissao;
    private OffsetDateTime datagravacao;
    private String emailusuario;
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

    public String getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(String statusPedido) {
        this.statusPedido = statusPedido;
    }

    public PedidoFlat(Integer id, Cliente cliente, TipoFrete tipoFrete, FormaPagamento formaPagamento, BigDecimal valorFrete,
                      CondPagamento condPagamento, BigDecimal comissao, OffsetDateTime datagravacao, String emailusuario) {
        this.id = id;
        this.cliente = cliente;
        this.tipoFrete = tipoFrete;
        this.formaPagamento = formaPagamento;
        this.valorFrete = valorFrete;
        this.condPagamento = condPagamento;
        this.comissao = comissao;
        this.datagravacao = datagravacao;
        this.emailusuario = emailusuario;
    }

    public PedidoFlat(Pedido ped, String ok) {
        this.id = ped.getId();
        this.cliente = ped.getCliente();
        this.formaPagamento = ped.getFormaPagamento();
        this.valorFrete = ped.getValorFrete();
        this.condPagamento = ped.getCondPagamento();
        this.comissao = ped.getComissao();
        this.tipoFrete = ped.getTipoFrete();
        this.statusPedido = ped.getStatusPedido();
        this.datagravacao = ped.getLogs().getDatagravacao();
        this.emailusuario = ped.getLogs().getEmailUsuario();

    }
}
