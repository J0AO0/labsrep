package com.mei.vendasapi.repository.query;

import com.mei.vendasapi.domain.Categoria;
import com.mei.vendasapi.domain.Empresa;
import com.mei.vendasapi.repository.filter.CategoriaFilter;
import com.mei.vendasapi.repository.filter.EmpresaFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmpresaRepositoryQuery {
    public Page<Empresa> filtrar(EmpresaFilter empresaFilter, Pageable pageable);
}
