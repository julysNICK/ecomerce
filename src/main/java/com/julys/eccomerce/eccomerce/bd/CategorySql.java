package com.julys.eccomerce.eccomerce.bd;

import org.springframework.data.jpa.repository.JpaRepository;

import com.julys.eccomerce.eccomerce.entity.Category;

public interface CategorySql extends JpaRepository<Category, Long> {

}