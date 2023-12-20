package com.julys.eccomerce.eccomerce.service.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.julys.eccomerce.eccomerce.dao.category.CategoryDAO;
import com.julys.eccomerce.eccomerce.entity.Category;

@Component
public class CategoryServiceImpl implements CategoryService {

  @Autowired
  private CategoryDAO categoryDAO;

  @Override
  public ResponseEntity<?> createCategory(Category category) {
    try {
      return ResponseEntity.ok(categoryDAO.createCategory(category));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }

  }

  @Override
  public ResponseEntity<?> getCategories() {

    try {
      return ResponseEntity.ok(categoryDAO.getCategories());
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @Override
  public ResponseEntity<?> getCategoryById(Long id) {
    try {

      Category category = categoryDAO.getCategoryById(id);

      if (category == null) {
        return ResponseEntity.badRequest().body("Category not found");
      }

      return ResponseEntity.ok(category);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @Override
  public ResponseEntity<?> deleteCategoryById(Long id) {
    try {
      categoryDAO.deleteCategoryById(id);
      return ResponseEntity.ok("Category deleted");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @Override
  public ResponseEntity<?> getCategoryByName(String name) {
    try {
      List<Category> category = categoryDAO.getCategoryByName(name);

      if (category == null) {
        return ResponseEntity.badRequest().body("Category not found");
      }

      return ResponseEntity.ok(category);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
