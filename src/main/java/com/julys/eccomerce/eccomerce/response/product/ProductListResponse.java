package com.julys.eccomerce.eccomerce.response.product;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.julys.eccomerce.eccomerce.entity.Product;

@Component
public class ProductListResponse {

  private String status;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  private String message;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  private List<Product> products;

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }

  public Map<String, Object> createJson() {
    Map<String, Object> map = new java.util.HashMap<>();
    map.put("status", this.status);
    map.put("message", this.message);
    map.put("products", this.products);
    return map;
  }

}