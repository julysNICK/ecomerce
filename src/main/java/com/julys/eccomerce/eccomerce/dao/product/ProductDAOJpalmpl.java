package com.julys.eccomerce.eccomerce.dao.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.julys.eccomerce.eccomerce.bd.ProductSql;
import com.julys.eccomerce.eccomerce.entity.Product;
import com.julys.eccomerce.eccomerce.util.Util;

@Component
public class ProductDAOJpalmpl implements ProductDAO {

  @Autowired
  private ProductSql productSql;

  @Override
  public Product findById(Long id) {
    Product product = productSql.findById(id).orElse(null);

    return product;

  }

  @Override
  public List<Product> allProducts() {
    List<Product> products = productSql.findAll();

    return products;
  }

  @Override
  public Product createProduct(Product product) {
    productSql.save(product);
    return product;
  }

  @Override
  public Product updateProduct(Long id, Product product) {
    var productToUpdate = productSql.findById(id).orElse(null);
    if (productToUpdate == null) {
      return null;
    }
    Util.copyNonNullProperties(product, productToUpdate);
    productSql.save(productToUpdate);
    return productToUpdate;
  }

  @Override
  public Product deleteProduct(Long id) {

    var product = productSql.findById(id).orElse(null);
    if (product == null) {
      return null;
    }

    productSql.delete(product);

    return product;
  }

  @Override
  public Iterable<Product> findByCategory(String category) {
    try {
      return productSql.findByCategoryName(category);
    } catch (org.springframework.dao.DataAccessResourceFailureException e) {
      throw new org.springframework.dao.DataAccessResourceFailureException(
          "Error finding products by category: " + e.getMessage());
    } catch (Exception e) {
      throw new NullPointerException("Error finding products by category: " + e.getMessage());
    }
  }
}