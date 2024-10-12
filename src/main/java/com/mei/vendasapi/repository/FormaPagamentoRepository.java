package com.mei.vendasapi.repository;

import com.mei.vendasapi.domain.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Integer> {

    @Query(value = "select * from forma_pagamento where id = ? and tenant_id = ?", nativeQuery = true)
    FormaPagamento findPorId(Integer id);

    @Query(value = "select * from forma_pagamento ", nativeQuery = true)
    List<FormaPagamento> findAllForm();

    FormaPagamento findByDescricao(String descricao);
}
