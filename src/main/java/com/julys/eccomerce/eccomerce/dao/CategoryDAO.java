package com.julys.eccomerce.eccomerce.dao;

import java.util.List;

import com.julys.eccomerce.eccomerce.entity.Category;

public interface CategoryDAO {

  Category createCategory(Category category);

  List<Category> getCategories();

  Category getCategoryById(Long id);

  String deleteCategoryById(Long id);
}