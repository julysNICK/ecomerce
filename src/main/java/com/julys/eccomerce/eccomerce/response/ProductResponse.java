package com.julys.eccomerce.eccomerce.response;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.julys.eccomerce.eccomerce.entity.Product;

@Component
public class ProductResponse {

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

  private Product product;

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Map<String, Object> createJson() {
    Map<String, Object> map = new java.util.HashMap<>();
    map.put("status", this.status);
    map.put("message", this.message);
    map.put("product", this.product);
    return map;
  }
}