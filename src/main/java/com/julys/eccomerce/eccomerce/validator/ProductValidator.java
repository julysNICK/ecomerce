package com.julys.eccomerce.eccomerce.validator;

import com.julys.eccomerce.eccomerce.entity.Product;

import java.util.List;

public class ProductValidator {

  Validator validator = new Validator();

  public List<String> validateProduct(Product product) {

    if (!validator.validateRequiredField(product.getName(), "Name is required")) {
      return List.of("Name is required");
    }

    if (!validator.validateRequiredField(product.getPrice(), "Price is required")) {
      return List.of("Price is required");
    }

    if (!validator.validateRequiredField(product.getStock(), "Stock is required")) {
      return List.of("Stock is required");
    }

    if (!validator.validateRequiredField(product.getDescription(), "Description is required")) {
      return List.of("Description is required");
    }

    return null;
  }

}