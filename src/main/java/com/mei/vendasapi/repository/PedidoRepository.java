package com.mei.vendasapi.repository;

import com.mei.vendasapi.domain.Item;
import com.mei.vendasapi.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Integer> {
    @Query(value = "SELECT * FROM pedido", nativeQuery = true)
    List<Pedido> findAllCat();

    @Query(value = "SELECT * FROM pedido where id = ?", nativeQuery = true)
    Pedido findPorId(Integer id);
}
