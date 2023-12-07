package com.julys.eccomerce.eccomerce.bd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.julys.eccomerce.eccomerce.entity.Category;

@Repository
public interface CategorySql extends JpaRepository<Category, Long> {

}