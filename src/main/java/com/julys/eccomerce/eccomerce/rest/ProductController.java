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
  public ResponseEntity findById(@PathVariable Long id) {
    HttpHeaders headers = new HttpHeaders();
    ProductResponse productResponseJson = new ProductResponse();

    headers.add("Custom-Header", "my-custom-header");

    Product product = productService.findById(id);

    if (product == null) {
      return new ErrorBuilder().buildResponseEntity(List.of("Product not found"), HttpStatus.NOT_FOUND);
    }

    productResponseJson.setStatus("OK");
    productResponseJson.setMessage("Product found");
    productResponseJson.setProduct(product);

    return new ResponseEntity<>(productResponseJson.createJson(), headers, HttpStatus.OK);
  }

  @PostMapping("/")
  public ResponseEntity<?> createProduct(@RequestBody Product product) {
    HttpHeaders headers = new HttpHeaders();
    ProductResponse productResponseJson = new ProductResponse();

    headers.add("Custom-Header", "my-custom-header");
    System.out.println("Product: " + product);

    List<String> errors = new ProductValidator().validateProduct(product);

    if (errors != null) {

      System.out.println("error builder: " + new ErrorBuilder().buildResponseEntity(errors, HttpStatus.BAD_REQUEST));
      return new ErrorBuilder().buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    Product response = productService.createProduct(product);
    productResponseJson.setStatus("OK");
    productResponseJson.setMessage("Product created");
    productResponseJson.setProduct(response);

    return new ResponseEntity<>(productResponseJson.createJson(), headers, HttpStatus.CREATED);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Product product) {
    ProductResponse productResponseJson = new ProductResponse();
    if (id == null) {
      ErrorFormat error = new ErrorFormat(HttpStatus.BAD_REQUEST, "Id is required");
      return new ErrorBuilder().buildResponseEntity(List.of(error.createError().get("error").toString()),
          HttpStatus.BAD_REQUEST);
    }

    Product response = productService.updateProduct(id, product);

    if (response == null) {
      return new ErrorBuilder().buildResponseEntity(List.of("Product not found"), HttpStatus.NOT_FOUND);
    }

    productResponseJson.setStatus("OK");
    productResponseJson.setMessage("Product updated");
    productResponseJson.setProduct(response);

    return new ResponseEntity<>(productResponseJson.createJson(), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
    ProductResponse productResponseJson = new ProductResponse();
    Product response = productService.deleteProduct(id);

    if (response == null) {
      return new ErrorBuilder().buildResponseEntity(List.of("Product not found"), HttpStatus.NOT_FOUND);
    }

    productResponseJson.setStatus("OK");
    productResponseJson.setMessage("Product deleted");
    productResponseJson.setProduct(response);

    return new ResponseEntity<>(productResponseJson.createJson(), HttpStatus.OK);
  }

  @GetMapping("/all")
  public ResponseEntity<?> allProducts() {
    ProductListResponse productListResponseJson = new ProductListResponse();

    List<Product> products = productService.allProducts();

    if (products == null) {
      ErrorFormat error = new ErrorFormat(HttpStatus.NOT_FOUND, "Products not found");

      return new ErrorBuilder().buildResponseEntity(List.of(error.createError().get("error").toString()),
          HttpStatus.NOT_FOUND);
    }

    productListResponseJson.setStatus("OK");
    productListResponseJson.setMessage("Products found");
    productListResponseJson.setProducts(products);

    return new ResponseEntity<>(productListResponseJson, HttpStatus.OK);
  }

  @GetMapping("/category/{name}")
  public ResponseEntity<?> getByCategoryName(@PathVariable String name) {
    Iterable<Product> products = productService.getByCategoryName(name);
    if (products == null || !products.iterator().hasNext()) {
      ErrorFormat error = new ErrorFormat(HttpStatus.NOT_FOUND, "Products not found");

      return new ErrorBuilder().buildResponseEntity(List.of(error.createError().get("error").toString()),
          HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(products, HttpStatus.OK);
  }

}