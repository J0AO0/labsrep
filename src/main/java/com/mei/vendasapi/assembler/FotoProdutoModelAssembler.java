package com.mei.vendasapi.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mei.vendasapi.domain.FotoProduto;
import com.mei.vendasapi.domain.dto.FotoProdutoDTO;

@Component
public class FotoProdutoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public FotoProdutoDTO toModel(FotoProduto foto) {
		return modelMapper.map(foto, FotoProdutoDTO.class);
	}
	
}
