package com.julys.eccomerce.eccomerce.bd;

import org.springframework.data.jpa.repository.JpaRepository;

import com.julys.eccomerce.eccomerce.entity.Product;

public interface ProductSql extends JpaRepository<Product, Long> {

}