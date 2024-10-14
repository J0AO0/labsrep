package com.mei.vendasapi.repository;

import com.mei.vendasapi.domain.Categoria;
import com.mei.vendasapi.domain.TipoFrete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoFreteRepository extends JpaRepository<TipoFrete, Integer> {
    @Query(value = "SELECT * FROM tipo_frete where status = 1", nativeQuery = true)
    List<TipoFrete> findAllCat();

    @Query(value = "SELECT * FROM tipo_frete where id = ?", nativeQuery = true)
    TipoFrete findPorId(Integer id);

    TipoFrete findByDescricao(String nome);

    @Query(value = "SELECT * FROM tipo_frete where status = 0 and tenant_id = ?", nativeQuery = true)
	List<TipoFrete> findAllSqlInativo(Integer buscarOuFalharInt);

    @Query(value = "SELECT * FROM tipo_frete where status = 1", nativeQuery = true)
	List<TipoFrete> findAllSql(Integer buscarOuFalharInt);

}
