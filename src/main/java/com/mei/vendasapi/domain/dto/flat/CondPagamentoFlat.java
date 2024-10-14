package com.mei.vendasapi.domain.dto.flat;

import com.mei.vendasapi.domain.CondPagamento;
import com.mei.vendasapi.domain.FormaPagamento;

import java.time.OffsetDateTime;

public class CondPagamentoFlat {

    private Integer id;
    private String descricao;
    private Boolean status;
    private OffsetDateTime datagravacao;
    private String emailusuario;
    private String statusPedido;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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

	public String getStatusPedido() {
		return statusPedido;
	}

	public void setStatusPedido(String statusPedido) {
		this.statusPedido = statusPedido;
	}



    public CondPagamentoFlat() {
		super();
	}

	public CondPagamentoFlat(Integer id, String descricao, Boolean status, OffsetDateTime datagravacao,
			String emailusuario, String statusPedido) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.status = status;
		this.datagravacao = datagravacao;
		this.emailusuario = emailusuario;
		this.statusPedido = statusPedido;
	}

	public CondPagamentoFlat(CondPagamento condPagamento, String ok) {
        this.id = condPagamento.getId();
        this.descricao = condPagamento.getDescricao();
        this.datagravacao = condPagamento.getLogs().getDatagravacao();
        this.emailusuario = condPagamento.getLogs().getEmailUsuario();

    }

    public CondPagamentoFlat(CondPagamento cat) {
        this.id = cat.getId();
        this.descricao = cat.getDescricao();
        this.status = cat.getStatus();
        this.datagravacao = cat.getLogs().getDatagravacao();
        this.emailusuario = cat.getLogs().getEmailUsuario();
    }


}
