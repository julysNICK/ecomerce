package com.julys.eccomerce.eccomerce.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.julys.eccomerce.eccomerce.dao.ProductDAO;
import com.julys.eccomerce.eccomerce.entity.Product;

/**
 * ProductServiceImpl
 */

@Component
public class ProductServiceImpl implements ProductService {

  private ProductDAO productDAO;

  public ProductServiceImpl(ProductDAO productDAO) {
    this.productDAO = productDAO;
  }

  @Override
  public Product findById(Long id) {
    return productDAO.findById(id);
  }

  @Override
  public List<Product> allProducts() {
    return productDAO.allProducts();
  }

  @Override
  public Product createProduct(Product product) {
    return productDAO.createProduct(product);
  }

  @Override
  public Product updateProduct(Long id, Product product) {
    return productDAO.updateProduct(id, product);
  }

  @Override
  public Product deleteProduct(Long id) {
    return productDAO.deleteProduct(id);
  }
}