package com.mei.vendasapi.domain.dto.flat;

import com.mei.vendasapi.domain.Cliente;
import com.mei.vendasapi.domain.Pedido;

import javax.persistence.ManyToOne;
import java.time.OffsetDateTime;

public class PedidoFlat {
    private Integer id;
    private Cliente cliente;
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

    public PedidoFlat(Integer id, Cliente cliente, OffsetDateTime datagravacao, String emailusuario) {
        this.id = id;
        this.cliente = cliente;
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
