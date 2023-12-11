package com.julys.eccomerce.eccomerce;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.julys.eccomerce.eccomerce.entity.Category;
import com.julys.eccomerce.eccomerce.entity.Product;
import com.julys.eccomerce.eccomerce.entity.ProductCategory;

@SpringBootTest
public class ProductCategorySqlTest {

  @MockBean
  private com.julys.eccomerce.eccomerce.bd.ProductCategorySql productCategorySql;

  @MockBean
  private com.julys.eccomerce.eccomerce.bd.ProductSql productSql;

  @MockBean
  private com.julys.eccomerce.eccomerce.bd.CategorySql categorySql;

  @Test
  public void testCreateCategoryProduct() {

    // create category
    Category category = new Category();

    category.setName("Test");

    // create product

    Product product = new Product();

    product.setName("Test");
    product.setPrice(new BigDecimal(10));
    product.setStock(new BigDecimal(10));

    // create product category

    ProductCategory productCategory = new ProductCategory();

    productCategory.setCategory(category);
    productCategory.setProductCategory(product);

    Mockito.when(productCategorySql.save(productCategory)).thenReturn(productCategory);

    assert (productCategorySql.save(productCategory).equals(productCategory));

  }
}