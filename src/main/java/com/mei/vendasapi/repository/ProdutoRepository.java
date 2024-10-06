package com.mei.vendasapi.repository;

import com.mei.vendasapi.domain.FotoProduto;
import com.mei.vendasapi.domain.Produto;
import com.mei.vendasapi.repository.query.ProdutoRepositoryQueries;
import com.mei.vendasapi.repository.query.ProdutoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>, ProdutoRepositoryQuery, ProdutoRepositoryQueries {
    @Query(value = "SELECT * FROM produto", nativeQuery = true)
    List<Produto> findAllCat();

    @Query(value = "SELECT * FROM produto where id = ?", nativeQuery = true)
    Produto findPorId(Integer id);

    Produto findByName(String name);
    
    @Query("select f from FotoProduto f join f.produto p "
			+ "where f.produto.id = :produtoId")
	Optional<FotoProduto> findFotoById(Integer produtoId);

}
