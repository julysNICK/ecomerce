package com.julys.eccomerce.eccomerce.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.julys.eccomerce.eccomerce.request.category.CategoryProductRequest;
import com.julys.eccomerce.eccomerce.service.categoryProduct.CategoryProductService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/category-product")
public class CategoryProductController {

  @Autowired
  private CategoryProductService categoryProductService;

  @PostMapping("/")
  public ResponseEntity<?> addProductToCategory(@RequestBody CategoryProductRequest entity) {

    try {
      return ResponseEntity
          .ok(categoryProductService.addProductToCategory(entity.getCategoryId(), entity.getProductId()));
    } catch (Exception e) {
      System.out.println(e.getMessage());

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

}