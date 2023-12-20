package com.julys.eccomerce.eccomerce.service.categoryProduct;

import org.springframework.http.ResponseEntity;

import com.julys.eccomerce.eccomerce.entity.ProductCategory;

public interface CategoryProductService {
  public ResponseEntity<?> addProductToCategory(Long categoryId, Long productId);
}