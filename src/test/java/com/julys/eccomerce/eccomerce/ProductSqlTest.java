package com.julys.eccomerce.eccomerce;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import com.julys.eccomerce.eccomerce.bd.ProductSql;
import com.julys.eccomerce.eccomerce.entity.Product;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

@SpringBootTest
public class ProductSqlTest {

  @MockBean
  private ProductSql productSql;

  // @BeforeAll
  // public void setUp() {
  // Product product = new Product();
  // product.setName("Test");
  // product.setPrice(new BigDecimal(10));
  // product.setStock(new BigDecimal(10));

  // Mockito.when(productSql.save(product)).thenReturn(product);
  // }

  @Test
  public void testCreateProduct() {

    Product product = new Product();
    product.setName("Test");
    product.setPrice(new BigDecimal(10));
    product.setStock(new BigDecimal(10));

    productSql.save(product);

    assertEquals(product.getName(), "Test");
    assertEquals(product.getPrice(), new BigDecimal(10));
    assertEquals(product.getStock(), new BigDecimal(10));
  }

  @Test
  public void testGetAllProducts() {

    List<Product> products = new ArrayList<Product>();

    Product product = new Product();

    product.setName("Test");
    product.setPrice(new BigDecimal(10));
    product.setStock(new BigDecimal(10));

    products.add(product);

    Product product2 = new Product();

    product2.setName("Test2");
    product2.setPrice(new BigDecimal(20));
    product2.setStock(new BigDecimal(20));

    products.add(product2);

    Mockito.when(productSql.findAll()).thenReturn(products);

    assertEquals(productSql.findAll(), products);

  }

  @Test
  public void testGetProductById() {

    Product product = new Product();

    product.setName("Test");
    product.setPrice(new BigDecimal(10));
    product.setStock(new BigDecimal(10));

    Mockito.when(productSql.findById(1L)).thenReturn(java.util.Optional.of(product));

    assertEquals(productSql.findById(1L).get(), product);

  }

  @Test
  public void testUpdateProduct() {

    Product product = new Product();

    product.setName("Test");
    product.setPrice(new BigDecimal(10));
    product.setStock(new BigDecimal(10));

    Mockito.when(productSql.findById(1L)).thenReturn(java.util.Optional.of(product));

    product.setName("Test2");
    product.setPrice(new BigDecimal(20));
    product.setStock(new BigDecimal(20));

    Mockito.when(productSql.save(product)).thenReturn(product);

    assertEquals(productSql.save(product), product);

  }

  @Test
  public void testDeleteProduct() {

    Mockito.doNothing().when(productSql).deleteById(1L);

    productSql.deleteById(1L);

    Mockito.verify(productSql, Mockito.times(1)).deleteById(1L);

  }

}