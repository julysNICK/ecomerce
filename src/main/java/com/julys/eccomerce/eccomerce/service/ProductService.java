package com.julys.eccomerce.eccomerce.service;

import java.util.List;

import com.julys.eccomerce.eccomerce.entity.Product;

/**
 * ProductService
 */
public interface ProductService {
  Product findById(Long id);

  List<Product> allProducts();

  Product createProduct(Product product);

  Product updateProduct(Long id, Product product);

  Product deleteProduct(Long id);

  Iterable<Product> getByCategoryName(String name);

}