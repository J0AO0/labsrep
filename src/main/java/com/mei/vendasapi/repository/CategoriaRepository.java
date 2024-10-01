package com.mei.vendasapi.repository;

import com.mei.vendasapi.domain.Categoria;
import com.mei.vendasapi.repository.query.CategoriaRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository <Categoria, Integer>, CategoriaRepositoryQuery {
    @Query(value = "SELECT * FROM categoria", nativeQuery = true)
    List<Categoria> findAllCat();

    @Query(value = "SELECT * FROM categoria where id = ?", nativeQuery = true)
    Categoria findPorId(Integer id);

	Categoria findByNome(String nome);


}
