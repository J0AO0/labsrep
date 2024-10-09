package com.mei.vendasapi.domain.dto.flat;

import com.mei.vendasapi.domain.Cliente;
import com.mei.vendasapi.domain.Pedido;

import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class PedidoFlat {
    private Integer id;
    private Cliente cliente;
    private String tipoFrete;
    private String tipoVenda;
    private BigDecimal valorFrete;
    private String condPagamento;
    private BigDecimal comissao;
    private OffsetDateTime datagravacao;
    private String emailusuario;

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

    public PedidoFlat(Integer id, Cliente cliente, String tipoFrete, String tipoVenda, BigDecimal valorFrete,
                      String condPagamento, BigDecimal comissao, OffsetDateTime datagravacao, String emailusuario) {
        this.id = id;
        this.cliente = cliente;
        this.tipoFrete = tipoFrete;
        this.tipoVenda = tipoVenda;
        this.valorFrete = valorFrete;
        this.condPagamento = condPagamento;
        this.comissao = comissao;
        this.datagravacao = datagravacao;
        this.emailusuario = emailusuario;
    }

    public PedidoFlat(Pedido ped, String ok) {
        this.id = ped.getId();
        this.cliente = ped.getCliente();
        this.datagravacao = ped.getLogs().getDatagravacao();
        this.emailusuario = ped.getLogs().getEmailUsuario();

    }
}
