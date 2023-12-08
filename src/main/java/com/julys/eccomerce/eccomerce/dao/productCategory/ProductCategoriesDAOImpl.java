package com.julys.eccomerce.eccomerce.dao.productCategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.julys.eccomerce.eccomerce.bd.ProductCategorySql;
import com.julys.eccomerce.eccomerce.entity.ProductCategory;

@Component
public class ProductCategoriesDAOImpl implements ProductCategoriesDAO {
  @Autowired
  private ProductCategorySql productCategorySql;

  @Override
  public ProductCategory createProductCategory(ProductCategory productCategory) {
    try {

      return productCategorySql.save(productCategory);

    } catch (org.springframework.dao.DataIntegrityViolationException e) {
      System.out.println("Error creating category: " + e.getMessage());
      if (e.getMessage().contains("product")) {
        throw new org.springframework.dao.DataIntegrityViolationException(
            "Error creating category, name is required");
      } else if (e.getMessage().contains("category")) {
        throw new org.springframework.dao.DataIntegrityViolationException(
            "Error creating category, description is required");
      }
      throw new org.springframework.dao.DataIntegrityViolationException(
          "Error creating category: " + e.getMessage());
    } catch (org.springframework.dao.DataAccessResourceFailureException e) {
      throw new org.springframework.dao.DataAccessResourceFailureException(
          "Error creating category: " + e.getMessage());
    } catch (Exception e) {

      throw new NullPointerException("Error creating productCategory: " + e.getMessage());
    }
  }
}