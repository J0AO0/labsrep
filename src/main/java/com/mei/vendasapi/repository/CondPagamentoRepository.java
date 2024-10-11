package com.mei.vendasapi.repository;

import com.mei.vendasapi.domain.CondPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CondPagamentoRepository extends JpaRepository<CondPagamento, Integer> {
    @Query(value = "SELECT * FROM cond_pagamento", nativeQuery = true)
    List<CondPagamento> findAllCat();

    @Query(value = "SELECT * FROM cond_pagamento where id = ?", nativeQuery = true)
    CondPagamento findPorId(Integer id);

    CondPagamento findByDescricao(String nome);
}

