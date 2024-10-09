package com.mei.vendasapi.repository.query;

import com.mei.vendasapi.domain.Produto;
import com.mei.vendasapi.domain.Usuario;
import com.mei.vendasapi.repository.filter.ProdutoFilter;
import com.mei.vendasapi.repository.filter.UsuarioFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioRepositoryQuery {
    public Page<Usuario> filtrar(UsuarioFilter usuarioFilter, Pageable pageable);
}
