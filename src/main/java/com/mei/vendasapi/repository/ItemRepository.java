package com.mei.vendasapi.repository;

import com.mei.vendasapi.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {
    @Query(value = "SELECT * FROM item", nativeQuery = true)
    List<Item> findAllCat();

    @Query(value = "SELECT * FROM item where id = ?", nativeQuery = true)
    Item findPorId(Integer id);
}
