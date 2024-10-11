package com.mei.vendasapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mei.vendasapi.domain.dto.ItemDTO;
import com.mei.vendasapi.domain.dto.ItemNewDTO;
import com.mei.vendasapi.domain.dto.PedidoDTO;
import com.mei.vendasapi.domain.dto.PedidoNewDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Pedido implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private TipoPedido tipoPedido;
    @ManyToOne
    private TipoFrete tipoFrete;
    @ManyToOne
    private FormaPagamento formaPagamento;
    private BigDecimal valorFrete;
    @ManyToOne
    private CondPagamento condPagamento;
    private BigDecimal comissao;
    private  String statusPedido;
    @ManyToOne
    private Tenant tenant;

    public Pedido(Integer id, Cliente cliente, TipoPedido tipoPedido, TipoFrete tipoFrete, FormaPagamento formaPagamento,
                  BigDecimal valorFrete, CondPagamento condPagamento, BigDecimal comissao,
                  Tenant tenant, List<LogSistema> logs, String statusPedido) {
        this.id = id;
        this.cliente = cliente;
        this.tipoPedido = tipoPedido;
        this.tipoFrete = tipoFrete;
        this.formaPagamento = formaPagamento;
        this.valorFrete = valorFrete;
        this.condPagamento = condPagamento;
        this.comissao = comissao;
        this.tenant = tenant;
        this.logs = logs;
        this.statusPedido = statusPedido;
    }

    public Pedido() {

    }

    public Integer getId() {
        return id;
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

    public void setId(Integer id) {
        this.id = id;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
	public Tenant getTenant() {
		return tenant;
	}
	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
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

    public Pedido(PedidoDTO obj){
        this.id = obj.getId();
        this.cliente = obj.getCliente();
    }

    public Pedido(PedidoNewDTO obj){
        this.id = obj.getId();
        this.formaPagamento = obj.getFormaPagamento();
        this.tipoPedido = obj.getTipoPedido();
        this.tipoFrete = obj.getTipoFrete();
        this.valorFrete = obj.getValorFrete();
        this.condPagamento = obj.getCondPagamento();
        this.comissao = obj.getComissao();
        this.cliente = obj.getCliente();
    }

    @JsonIgnore
    @OneToMany(mappedBy = "pedido")
    private List<LogSistema> logs = new ArrayList<LogSistema>();

    public void addLogs(LogSistema log) {
        logs.add(log);
    }

    public LogSistema getLogs() {
        Integer codigo = 0;
        Integer indice = -1;
        LogSistema ultimo = new LogSistema();
        for (int i = 0; i < logs.size(); i++) {
            if (codigo < logs.get(i).getId()) {
                codigo = logs.get(i).getId();
                indice = i;
            }
        }
        if (indice==-1) {
            return ultimo;
        }else {
            return ultimo = logs.get(indice);
        }

    }

    public void setLogs(List<LogSistema> logs) {
        this.logs = logs;
    }

}
