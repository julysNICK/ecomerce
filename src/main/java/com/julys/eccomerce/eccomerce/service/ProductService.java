package com.julys.eccomerce.eccomerce.service;

import java.util.List;

import com.julys.eccomerce.eccomerce.entity.Product;

/**
 * ProductService
 */
public interface ProductService {
 Product findById(Long id);
 List<Product> allProducts();
 String createProduct(Product product);
  
 String updateProduct(Long id, Product product);

  String deleteProduct(Long id);
  
}