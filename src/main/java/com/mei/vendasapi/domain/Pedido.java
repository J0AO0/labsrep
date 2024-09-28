package com.mei.vendasapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mei.vendasapi.domain.dto.ItemDTO;
import com.mei.vendasapi.domain.dto.ItemNewDTO;
import com.mei.vendasapi.domain.dto.PedidoDTO;
import com.mei.vendasapi.domain.dto.PedidoNewDTO;

import java.io.Serializable;
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
    private Tenant tenant;

    public Integer getId() {
        return id;
    }
    public Pedido() {
        super();
    }
    public Pedido(Integer id, Cliente cliente, Tenant tenant, TipoPedido tipoPedido) {
        super();
        this.id = id;
        this.cliente = cliente;
        this.tenant = tenant;
        this.tipoPedido = tipoPedido;
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

    public Pedido(PedidoDTO obj){
        this.id = obj.getId();
        this.cliente = obj.getCliente();
    }

    public Pedido(PedidoNewDTO obj){
        this.id = obj.getId();
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
