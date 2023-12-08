package com.julys.eccomerce.eccomerce.service.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.julys.eccomerce.eccomerce.dao.category.CategoryDAO;
import com.julys.eccomerce.eccomerce.entity.Category;

@Component
public class CategoryServiceImpl implements CategoryService {

  @Autowired
  private CategoryDAO categoryDAO;

  @Override
  public Category createCategory(Category category) {

    return categoryDAO.createCategory(category);

  }

  @Override
  public java.util.List<Category> getCategories() {
    return categoryDAO.getCategories();
  }

  @Override
  public Category getCategoryById(Long id) {
    return categoryDAO.getCategoryById(id);
  }

  @Override
  public String deleteCategoryById(Long id) {
    return categoryDAO.deleteCategoryById(id);
  }
}
