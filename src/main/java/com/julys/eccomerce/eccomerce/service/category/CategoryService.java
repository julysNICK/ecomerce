package com.julys.eccomerce.eccomerce.service.category;

import com.julys.eccomerce.eccomerce.entity.Category;
import java.util.List;

public interface CategoryService {
  public Category createCategory(Category category);

  public List<Category> getCategories();

  public Category getCategoryById(Long id);

  public String deleteCategoryById(Long id);
}