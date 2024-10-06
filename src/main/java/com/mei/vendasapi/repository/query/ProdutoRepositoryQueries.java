package com.mei.vendasapi.repository.query;

import com.mei.vendasapi.domain.FotoProduto;

public interface ProdutoRepositoryQueries {

	FotoProduto save(FotoProduto foto);

	void delete(FotoProduto foto);

}
