package com.mei.vendasapi.domain.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.mei.vendasapi.validation.core.FileContentType;
import com.mei.vendasapi.validation.core.FileSize;

public class FotoProdutoInput {
	@NotNull
	@FileSize(max = "500KB")
	@FileContentType(allowed = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
	private MultipartFile arquivo;
	
	@NotBlank
	private String descricao;

	public FotoProdutoInput() {
	}

	public FotoProdutoInput(MultipartFile arquivo, String descricao) {
		this.arquivo = arquivo;
		this.descricao = descricao;
	}

	public MultipartFile getArquivo() {
		return arquivo;
	}

	public void setArquivo(MultipartFile arquivo) {
		this.arquivo = arquivo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
