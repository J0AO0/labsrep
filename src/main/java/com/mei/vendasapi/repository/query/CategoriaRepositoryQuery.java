package com.mei.vendasapi.repository.query;

import com.mei.vendasapi.domain.Categoria;
import com.mei.vendasapi.repository.filter.CategoriaFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoriaRepositoryQuery {
    public Page<Categoria> filtrar(CategoriaFilter produtoFilter, Pageable pageable);
}
