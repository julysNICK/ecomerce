package com.julys.eccomerce.eccomerce.dao.product;

import java.util.List;

import com.julys.eccomerce.eccomerce.entity.Product;
import com.julys.eccomerce.eccomerce.entity.User;

/**
 * ProductDAO
 */
public interface ProductDAO {
  Product findById(Long id);

  List<Product> allProducts();

  Product createProduct(Product product);

  Product updateProduct(Long id, Product product);

  Product deleteProduct(Long id);

  Iterable<Product> findByCategory(String category);
}
