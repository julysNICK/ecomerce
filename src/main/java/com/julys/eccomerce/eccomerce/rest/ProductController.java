package com.julys.eccomerce.eccomerce.rest;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.mapping.Map;
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
import com.julys.eccomerce.eccomerce.service.ProductService;

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
  public ResponseEntity findById(@PathVariable Long id) {
    HttpHeaders headers = new HttpHeaders();

    headers.add("Custom-Header", "my-custom-header");

    Product product = productService.findById(id);

    if (product == null) {
      return new ErrorBuilder().buildResponseEntity(List.of("Product not found"), HttpStatus.NOT_FOUND);
    }

    HashMap<String, Object> response = new HashMap<>();

    response.put("status", HttpStatus.OK);
    response.put("product", product);

    return new ResponseEntity<>(response, headers, HttpStatus.OK);
  }

  @GetMapping("/")
  public String index() {
    return "Hello World";
  }

  @PostMapping("/")
  public ResponseEntity createProduct(@RequestBody Product product) {
    HttpHeaders headers = new HttpHeaders();

    headers.add("Custom-Header", "my-custom-header");

    List<String> errors = new ProductValidator().validateProduct(product);

    if (errors != null) {
      return new ErrorBuilder().buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(productService.createProduct(product), headers, HttpStatus.CREATED);
  }

  @PatchMapping("/{id}")
  public ResponseEntity updateProduct(@PathVariable Long id, @RequestBody Product product) {

    if (id == null) {
      ErrorFormat error = new ErrorFormat(HttpStatus.BAD_REQUEST, "Id is required");
      return new ErrorBuilder().buildResponseEntity(List.of(error.createError().get("error").toString()),
          HttpStatus.BAD_REQUEST);
    }

    var response = productService.updateProduct(id, product);

    if (response == null) {
      return new ErrorBuilder().buildResponseEntity(List.of("Product not found"), HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteProduct(@PathVariable Long id) {
    String response = productService.deleteProduct(id);

    if (response == null) {
      return new ErrorBuilder().buildResponseEntity(List.of("Product not found"), HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/all")
  public ResponseEntity allProducts() {
    HttpHeaders headers = new HttpHeaders();

    headers.add("Custom-Header", "my-custom-header");

    LinkedHashMap<String, Object> response = new LinkedHashMap<>();

    List<Product> products = productService.allProducts();

    if (products == null) {
      ErrorFormat error = new ErrorFormat(HttpStatus.NOT_FOUND, "Products not found");

      return new ErrorBuilder().buildResponseEntity(List.of(error.createError().get("error").toString()),
          HttpStatus.NOT_FOUND);
    }

    response.put("status", HttpStatus.OK);
    response.put("products", products);

    return new ResponseEntity<>(response, headers, HttpStatus.OK);
  }

}