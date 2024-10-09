package com.mei.vendasapi.repository.filter;

import com.mei.vendasapi.domain.Cliente;
import com.mei.vendasapi.domain.Tenant;
import com.mei.vendasapi.domain.TipoPedido;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Date;

public class PedidoFilter {

    private Integer id;
    private Cliente cliente;
    private TipoPedido tipoPedido;
    private String tipoFrete;
    private String tipoVenda;
    private BigDecimal valorFrete;
    private String condPagamento;
    private BigDecimal comissao;
    private String emailusuario;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datagravacaode;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datagravacaoate;

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

    public String getEmailusuario() {
        return emailusuario;
    }

    public void setEmailusuario(String emailusuario) {
        this.emailusuario = emailusuario;
    }

    public Date getDatagravacaode() {
        return datagravacaode;
    }

    public void setDatagravacaode(Date datagravacaode) {
        this.datagravacaode = datagravacaode;
    }

    public Date getDatagravacaoate() {
        return datagravacaoate;
    }

    public void setDatagravacaoate(Date datagravacaoate) {
        this.datagravacaoate = datagravacaoate;
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

    public PedidoFilter(Integer id, Cliente cliente, TipoPedido tipoPedido, String tipoFrete,
                        String tipoVenda, BigDecimal valorFrete, String condPagamento, BigDecimal comissao,
                        String emailusuario, Date datagravacaode, Date datagravacaoate) {
        this.id = id;
        this.cliente = cliente;
        this.tipoPedido = tipoPedido;
        this.tipoFrete = tipoFrete;
        this.tipoVenda = tipoVenda;
        this.valorFrete = valorFrete;
        this.condPagamento = condPagamento;
        this.comissao = comissao;
        this.emailusuario = emailusuario;
        this.datagravacaode = datagravacaode;
        this.datagravacaoate = datagravacaoate;
    }

    public PedidoFilter() {
    }
}
