package com.julys.eccomerce.eccomerce.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.julys.eccomerce.eccomerce.entity.Product;
import com.julys.eccomerce.eccomerce.error.ErrorBuilder;
import com.julys.eccomerce.eccomerce.error.ErrorFormat;
import com.julys.eccomerce.eccomerce.response.product.ProductListResponse;
import com.julys.eccomerce.eccomerce.response.product.ProductResponse;
import com.julys.eccomerce.eccomerce.service.product.ProductService;
import com.julys.eccomerce.eccomerce.validator.ProductValidator;

/**
 * ProductController
 */

@RestController
@RequestMapping("/api/products")
public class ProductController {

  private ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/hello")
  public String hello() {
    return "Hello World";
  }

  @GetMapping("/{id}")
  @ResponseBody
  public ResponseEntity<?> findById(@PathVariable Long id) {
    return productService.findById(id);
  }

  @PostMapping("/")
  public ResponseEntity<?> createProduct(@RequestBody Product product) {

    return productService.createProduct(product);

  }

  @PatchMapping("/update/{id}")
  public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Product product) {

    return productService.updateProduct(id, product);

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
    return productService.deleteProduct(id);

  }

  @GetMapping("/all")
  public ResponseEntity<?> allProducts() {
    return productService.allProducts();

  }

  @GetMapping("/category/{name}")
  public ResponseEntity<?> getByCategoryName(@PathVariable String name) {
    return productService.getByCategoryName(name);

  }

}