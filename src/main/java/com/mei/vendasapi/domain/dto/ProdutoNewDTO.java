package com.mei.vendasapi.domain.dto;

import java.math.BigDecimal;

import com.mei.vendasapi.domain.Categoria;
import com.mei.vendasapi.validation.produto.ProdutoInsert;

@ProdutoInsert
public class ProdutoNewDTO {
	private Integer id;
	private String name;
	private Categoria categoria;
	private BigDecimal preco;
	private String descricao;
	private Boolean status = Boolean.TRUE;
	private String qrCode;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public ProdutoNewDTO(Integer id, String name, Categoria categoria, BigDecimal preco, String descricao,
			Boolean status, String qrCode) {
		this.id = id;
		this.name = name;
		this.categoria = categoria;
		this.preco = preco;
		this.descricao = descricao;
		this.status = status;
		this.qrCode = qrCode;
	}




	public ProdutoNewDTO() {
	}
}
