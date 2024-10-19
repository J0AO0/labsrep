package com.mei.vendasapi.domain.dto.flat;

import java.time.OffsetDateTime;

import com.mei.vendasapi.domain.TipoPedido;

public class TipoPedidoFlat {

	private Integer id;
	private String nome;
	private Boolean status = Boolean.TRUE;
	private OffsetDateTime datagravacao;
	private String emailusuario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public TipoPedidoFlat(Integer id, String nome, Boolean status, OffsetDateTime datagravacao, String emailusuario) {
		this.id = id;
		this.nome = nome;
		this.status = status;
		this.datagravacao = datagravacao;
		this.emailusuario = emailusuario;
	}

	public TipoPedidoFlat(TipoPedido obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.datagravacao = obj.getLogs().getDatagravacao();
		this.emailusuario = obj.getLogs().getEmailUsuario();
		this.status = obj.getStatus();
	}

}
