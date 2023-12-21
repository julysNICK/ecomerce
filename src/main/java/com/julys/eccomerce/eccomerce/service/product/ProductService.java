package com.julys.eccomerce.eccomerce.service.product;

import org.springframework.http.ResponseEntity;

import com.julys.eccomerce.eccomerce.entity.Product;

/**
 * ProductService
 */
public interface ProductService {
  ResponseEntity<?> findById(Long id);

  ResponseEntity<?> allProducts();

  ResponseEntity<?> createProduct(Product product);

  ResponseEntity<?> updateProduct(Long id, Product product);

  ResponseEntity<?> deleteProduct(Long id);

  ResponseEntity<?> getByCategoryName(String name);

}