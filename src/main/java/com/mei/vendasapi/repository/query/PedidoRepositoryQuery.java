package com.mei.vendasapi.repository.query;

import com.mei.vendasapi.domain.Pedido;
import com.mei.vendasapi.repository.filter.PedidoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PedidoRepositoryQuery {
    public Page<Pedido> filtrar(PedidoFilter pedidoFilter, Pageable pageable);
}
