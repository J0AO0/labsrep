package com.mei.vendasapi.repository;

import com.mei.vendasapi.domain.Produto;
import com.mei.vendasapi.repository.query.ProdutoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>, ProdutoRepositoryQuery {
    @Query(value = "SELECT * FROM produto", nativeQuery = true)
    List<Produto> findAllCat();

    @Query(value = "SELECT * FROM produto where id = ?", nativeQuery = true)
    Produto findPorId(Integer id);

    Produto findByName(String name);
}
