package com.julys.eccomerce.eccomerce.dao;

import java.util.List;

import com.julys.eccomerce.eccomerce.entity.Product;
import com.julys.eccomerce.eccomerce.entity.User;

/**
 * ProductDAO
 */
public interface ProductDAO {
  Product findById(Long id);

  List<Product> allProducts();

  String createProduct(Product product);

  String updateProduct(Long id, Product product);

  String deleteProduct(Long id);
}
