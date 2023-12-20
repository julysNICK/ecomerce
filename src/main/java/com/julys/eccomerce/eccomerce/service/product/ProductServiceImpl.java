package com.julys.eccomerce.eccomerce.service.product;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.julys.eccomerce.eccomerce.dao.product.ProductDAO;
import com.julys.eccomerce.eccomerce.entity.Product;
import com.julys.eccomerce.eccomerce.error.ErrorBuilder;
import com.julys.eccomerce.eccomerce.error.ErrorFormat;
import com.julys.eccomerce.eccomerce.response.product.ProductListResponse;
import com.julys.eccomerce.eccomerce.response.product.ProductResponse;
import com.julys.eccomerce.eccomerce.validator.ProductValidator;

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
  public ResponseEntity<?> findById(Long id) {

    try {
      HttpHeaders headers = new HttpHeaders();
      ProductResponse productResponseJson = new ProductResponse();

      headers.add("Custom-Header", "my-custom-header");

      Product product = productDAO.findById(id);

      if (product == null) {
        return new ErrorBuilder().buildResponseEntity(List.of("Product not found"), HttpStatus.NOT_FOUND);
      }

      productResponseJson.setStatus("OK");
      productResponseJson.setMessage("Product found");
      productResponseJson.setProduct(product);

      return new ResponseEntity<>(productResponseJson.createJson(), headers, HttpStatus.OK);
    } catch (Exception e) {
      return new ErrorBuilder().buildResponseEntity(List.of("Product not found"), HttpStatus.NOT_FOUND);
    }
  }

  @Override
  public ResponseEntity<?> allProducts() {

    try {
      ProductListResponse productListResponseJson = new ProductListResponse();

      List<Product> products = productDAO.allProducts();

      if (products == null) {
        ErrorFormat error = new ErrorFormat(HttpStatus.NOT_FOUND, "Products not found");

        return new ErrorBuilder().buildResponseEntity(List.of(error.createError().get("error").toString()),
            HttpStatus.NOT_FOUND);
      }

      productListResponseJson.setStatus("OK");
      productListResponseJson.setMessage("Products found");
      productListResponseJson.setProducts(products);

      return new ResponseEntity<>(productListResponseJson, HttpStatus.OK);
    } catch (Exception e) {

      return new ErrorBuilder().buildResponseEntity(List.of("Products not found"), HttpStatus.NOT_FOUND);
    }
  }

  @Override
  public ResponseEntity<?> createProduct(Product product) {

    try {
      HttpHeaders headers = new HttpHeaders();
      ProductResponse productResponseJson = new ProductResponse();

      headers.add("Custom-Header", "my-custom-header");
      System.out.println("Product: " + product);

      List<String> errors = new ProductValidator().validateProduct(product);

      if (errors != null) {

        System.out.println("error builder: " + new ErrorBuilder().buildResponseEntity(errors, HttpStatus.BAD_REQUEST));
        return new ErrorBuilder().buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
      }

      Product response = productDAO.createProduct(product);
      productResponseJson.setStatus("OK");
      productResponseJson.setMessage("Product created");
      productResponseJson.setProduct(response);

      return new ResponseEntity<>(productResponseJson.createJson(), headers, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ErrorBuilder().buildResponseEntity(List.of("Product not found"), HttpStatus.NOT_FOUND);
    }
  }

  @Override
  public ResponseEntity<?> updateProduct(Long id, Product product) {

    try {
      ProductResponse productResponseJson = new ProductResponse();

      if (id == null) {
        ErrorFormat error = new ErrorFormat(HttpStatus.BAD_REQUEST, "Id is required");
        return new ErrorBuilder().buildResponseEntity(List.of(error.createError().get("error").toString()),
            HttpStatus.BAD_REQUEST);
      }

      Product response = productDAO.updateProduct(id, product);

      if (response == null) {
        return new ErrorBuilder().buildResponseEntity(List.of("Product not found"), HttpStatus.NOT_FOUND);
      }

      productResponseJson.setStatus("OK");
      productResponseJson.setMessage("Product updated");
      productResponseJson.setProduct(response);

      return new ResponseEntity<>(productResponseJson.createJson(), HttpStatus.OK);
    } catch (Exception e) {
      return new ErrorBuilder().buildResponseEntity(List.of("Product not found"), HttpStatus.NOT_FOUND);
    }
  }

  @Override
  public ResponseEntity<?> deleteProduct(Long id) {
    // return productDAO.deleteProduct(id);
    try {
      ProductResponse productResponseJson = new ProductResponse();
      Product response = productDAO.deleteProduct(id);

      if (response == null) {
        return new ErrorBuilder().buildResponseEntity(List.of("Product not found"), HttpStatus.NOT_FOUND);
      }

      productResponseJson.setStatus("OK");
      productResponseJson.setMessage("Product deleted");
      productResponseJson.setProduct(response);

      return new ResponseEntity<>(productResponseJson.createJson(), HttpStatus.OK);
    } catch (Exception e) {
      // TODO: handle exception

      return new ErrorBuilder().buildResponseEntity(List.of("Product not found"), HttpStatus.NOT_FOUND);
    }
  }

  @Override
  public ResponseEntity<?> getByCategoryName(String name) {
    try {
      Iterable<Product> products = productDAO.findByCategory(name);
      if (products == null || !products.iterator().hasNext()) {
        ErrorFormat error = new ErrorFormat(HttpStatus.NOT_FOUND, "Products not found");

        return new ErrorBuilder().buildResponseEntity(List.of(error.createError().get("error").toString()),
            HttpStatus.NOT_FOUND);
      }

      return new ResponseEntity<>(products, HttpStatus.OK);
    } catch (Exception e) {
      // TODO: handle exception

      return new ErrorBuilder().buildResponseEntity(List.of("Product not found"), HttpStatus.NOT_FOUND);
    }
  }
}