package com.mei.vendasapi.repository;

import com.mei.vendasapi.domain.CondPagamento;
import com.mei.vendasapi.domain.FormaPagamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CondPagamentoRepository extends JpaRepository<CondPagamento, Integer> {
    @Query(value = "SELECT * FROM cond_pagamento where status = 1", nativeQuery = true)
    List<CondPagamento> findAllCat();

    @Query(value = "SELECT * FROM cond_pagamento where id = ?", nativeQuery = true)
    CondPagamento findPorId(Integer id);

    CondPagamento findByDescricao(String nome);

    @Query(value = "SELECT * FROM cond_pagamento where status=0 and tenant_id = ?", nativeQuery = true)
	List<CondPagamento> findAllSqlInativo(Integer buscarOuFalharInt);

    @Query(value = "SELECT * FROM cond_pagamento where status = 1", nativeQuery = true)
	List<CondPagamento> findAllSql(Integer buscarOuFalharInt);
}

