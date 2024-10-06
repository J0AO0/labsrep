package com.mei.vendasapi.service;

import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mei.vendasapi.domain.FotoProduto;
import com.mei.vendasapi.repository.ProdutoRepository;
import com.mei.vendasapi.service.FotoStorageService.NovaFoto;
import com.mei.vendasapi.service.exception.FotoProdutoNaoEncontradaException;

@Service
public class CatalogoFotoProdutoService {

	@Autowired
	private FotoStorageService fotoStorage;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Transactional
	public FotoProduto salvar(FotoProduto foto, InputStream dadosArquivo) {
		Integer produtoId = foto.getProduto().getId();
		String nomeNovoArquivo = fotoStorage.gerarNomeArquivo(foto.getNomeArquivo());
		String nomeArquivoExistente = null;

		Optional<FotoProduto> fotoExistente = produtoRepository.findFotoById(produtoId);

		if (fotoExistente.isPresent()) {
			nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
			// produtoRepository.delete(fotoExistente.get());
		}

		foto.setNomeArquivo(nomeNovoArquivo);
		foto = produtoRepository.save(foto);
		produtoRepository.flush();

		NovaFoto novaFoto = NovaFoto.builder().nomeAquivo(foto.getNomeArquivo()).inputStream(dadosArquivo).build();

		fotoStorage.substituir(nomeArquivoExistente, novaFoto);

		return foto;
	}

	public FotoProduto buscarOuFalhar(Integer produtoId) {
		return produtoRepository.findFotoById(produtoId)
				.orElseThrow(() -> new FotoProdutoNaoEncontradaException(produtoId));
	}

	@Transactional
	public void excluir(Integer produtoId) {
		FotoProduto foto = buscarOuFalhar(produtoId);

		// produtoRepository.delete(foto);
		produtoRepository.flush();

		fotoStorage.remover(foto.getNomeArquivo());
	}

}
