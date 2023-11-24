package com.julys.eccomerce.eccomerce.dao;

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
  public String createProduct(Product product) {
    productSql.save(product);
    return "Product created";
  }

  @Override
  public String updateProduct(Long id, Product product) {
    var productToUpdate = productSql.findById(id).orElse(null);
    if (productToUpdate == null) {
      return null;
    }
    Util.copyNonNullProperties(product, productToUpdate);
    productSql.save(productToUpdate);
    return "Product updated";
  }

  @Override
  public String deleteProduct(Long id) {

    var product = productSql.findById(id).orElse(null);
    if (product == null) {
      return null;
    }

    productSql.delete(product);

    return "Product deleted";
  }

}