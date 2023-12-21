package com.julys.eccomerce.eccomerce.service.categoryProduct;

import org.springframework.http.ResponseEntity;

public interface CategoryProductService {
  public ResponseEntity<?> addProductToCategory(Long categoryId, Long productId);
}