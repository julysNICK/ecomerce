package com.julys.eccomerce.eccomerce.bd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.julys.eccomerce.eccomerce.entity.Category;
import java.util.List;

@Repository
public interface CategorySql extends JpaRepository<Category, Long> {

  // create query with operator like and letter lowercase

  @Query("SELECT c FROM Category c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', ?1, '%'))")
  List<Category> findByCategoryByName(String name);
}