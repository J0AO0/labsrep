package com.mei.vendasapi.repository.query;

import com.mei.vendasapi.domain.Produto;
import com.mei.vendasapi.repository.filter.ProdutoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProdutoRepositoryQuery {
    public Page<Produto> filtrar(ProdutoFilter produtoFilter, Pageable pageable);
}
