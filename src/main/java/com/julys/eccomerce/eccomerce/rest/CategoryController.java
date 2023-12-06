package com.julys.eccomerce.eccomerce.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.julys.eccomerce.eccomerce.entity.Category;
import com.julys.eccomerce.eccomerce.service.CategoryService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
  @Autowired
  CategoryService categoryService;

  @RequestMapping("/hello")
  public String hello() {
    return "Hello World Category";
  }

  @PostMapping("/")
  public ResponseEntity<?> createCategory(@RequestBody Category entity) {
    try {
      System.out.println("entity: " + entity.getName() + " ");
      return ResponseEntity.ok(categoryService.createCategory(entity));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/")
  public ResponseEntity<?> getMethodName() {
    try {
      return ResponseEntity.ok(categoryService.getCategories());
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
    try {

      Category category = categoryService.getCategoryById(id);

      if (category == null) {
        return ResponseEntity.badRequest().body("Category not found");
      }

      return ResponseEntity.ok(category);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteCategoryById(@PathVariable Long id) {
    try {
      return ResponseEntity.ok(categoryService.deleteCategoryById(id));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

}