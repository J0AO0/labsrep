package com.mei.vendasapi.repository;


import com.mei.vendasapi.domain.Codigo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CodigoRepository extends JpaRepository<Codigo, Integer> {

	@Query(value= "SELECT * FROM codigo where id = ?", nativeQuery = true)
	Codigo findPorId(Integer id);
	@Query(value= "SELECT * FROM codigo where telefone = ?", nativeQuery = true)
	Codigo findPorTelefone(String id);

	Codigo findByTelefone(String telefona);
	
	
}
