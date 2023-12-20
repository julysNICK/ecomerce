package com.julys.eccomerce.eccomerce.service.category;

import com.julys.eccomerce.eccomerce.entity.Category;
import java.util.List;

import org.springframework.http.ResponseEntity;

public interface CategoryService {
  public ResponseEntity<?> createCategory(Category category);

  public ResponseEntity<?> getCategories();

  public ResponseEntity<?> getCategoryById(Long id);

  public ResponseEntity<?> deleteCategoryById(Long id);

  public ResponseEntity<?> getCategoryByName(String name);
}