package com.julys.eccomerce.eccomerce.bd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.julys.eccomerce.eccomerce.entity.Product;

@Repository
public interface ProductSql extends JpaRepository<Product, Long> {

  @Query("SELECT p FROM Product p JOIN p.productCategories pc JOIN pc.category c WHERE c.name = :categoryName")
  Iterable<Product> findByCategoryName(String categoryName);
}