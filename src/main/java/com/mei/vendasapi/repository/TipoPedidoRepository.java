package com.mei.vendasapi.repository;

import com.mei.vendasapi.domain.Produto;
import com.mei.vendasapi.domain.TipoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoPedidoRepository extends JpaRepository<TipoPedido, Integer> {
    @Query(value = "SELECT * FROM tipo_pedido", nativeQuery = true)
    List<TipoPedido> findAllCat();

    @Query(value = "SELECT * FROM tipo_pedido where id = ?", nativeQuery = true)
    TipoPedido findPorId(Integer id);

    TipoPedido findByNome(String name);
}
