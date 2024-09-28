package com.mei.vendasapi.repository;

import com.mei.vendasapi.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    @Query(value = "SELECT * FROM cliente", nativeQuery = true)
    List<Cliente> findAll();

    @Query(value = "SELECT * FROM cliente where id = ?", nativeQuery = true)
    Cliente findPorId(Integer id);

    Cliente findByCpfoucnpj(String cpfoucnpj);
}
