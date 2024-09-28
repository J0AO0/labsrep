package com.mei.vendasapi.repository;

import com.mei.vendasapi.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository <Categoria, Integer> {
    @Query(value = "SELECT * FROM categoria", nativeQuery = true)
    List<Categoria> findAllCat();

    @Query(value = "SELECT * FROM categoria where id = ?", nativeQuery = true)
    Categoria findPorId(Integer id);

	Categoria findByNome(String nome);


}
