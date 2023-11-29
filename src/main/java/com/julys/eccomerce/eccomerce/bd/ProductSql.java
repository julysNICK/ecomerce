package com.julys.eccomerce.eccomerce.bd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.julys.eccomerce.eccomerce.entity.Product;

@Repository
public interface ProductSql extends JpaRepository<Product, Long> {

}