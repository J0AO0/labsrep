package com.mei.vendasapi.domain.dto;

public class TipoPedidoDTO {
	private Integer id;
	private String nome;
	private Boolean status = Boolean.TRUE;

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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public TipoPedidoDTO(Integer id, String nome, Boolean status) {
		super();
		this.id = id;
		this.nome = nome;
		this.status = status;
	}

	public TipoPedidoDTO() {
	}
}
