package com.mei.vendasapi.domain.dto;

public class FotoProdutoDTO {

	private String nomeArquivo;
	private String descricao;
	private String contentType;
	private Long tamanho;

	public FotoProdutoDTO() {
	}

	public FotoProdutoDTO(String nomeArquivo, String descricao, String contentType, Long tamanho) {
		this.nomeArquivo = nomeArquivo;
		this.descricao = descricao;
		this.contentType = contentType;
		this.tamanho = tamanho;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Long getTamanho() {
		return tamanho;
	}

	public void setTamanho(Long tamanho) {
		this.tamanho = tamanho;
	}

}
