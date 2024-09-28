package com.mei.vendasapi.domain.dto;

import com.mei.vendasapi.validation.categoria.CategoriaInsert;
import com.mei.vendasapi.validation.categoria.CategoriaUpdate;


@CategoriaInsert
public class CategoriaNewDTO {
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

	public CategoriaNewDTO(Integer id, String nome, Boolean status) {
		this.id = id;
		this.nome = nome;
		this.status = status;
	}

	public CategoriaNewDTO() {
	}
}
