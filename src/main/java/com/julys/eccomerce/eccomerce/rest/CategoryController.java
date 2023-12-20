package com.julys.eccomerce.eccomerce.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.julys.eccomerce.eccomerce.entity.Category;
import com.julys.eccomerce.eccomerce.service.category.CategoryService;

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
    return categoryService.createCategory(entity);
  }

  @GetMapping("/")
  public ResponseEntity<?> getMethodName() {

    return categoryService.getCategories();

  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
    return categoryService.getCategoryById(id);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteCategoryById(@PathVariable Long id) {
    return categoryService.deleteCategoryById(id);
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<?> getCategoryByName(@PathVariable String name) {
    return categoryService.getCategoryByName(name);
  }

}