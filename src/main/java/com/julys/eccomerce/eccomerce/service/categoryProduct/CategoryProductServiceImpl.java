package com.julys.eccomerce.eccomerce.service.categoryProduct;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.julys.eccomerce.eccomerce.dao.category.CategoryDAO;
import com.julys.eccomerce.eccomerce.dao.productCategory.ProductCategoriesDAO;
import com.julys.eccomerce.eccomerce.entity.Category;
import com.julys.eccomerce.eccomerce.entity.Product;
import com.julys.eccomerce.eccomerce.entity.ProductCategory;
import com.julys.eccomerce.eccomerce.entity.ProductCategoryId;
import com.julys.eccomerce.eccomerce.service.category.CategoryService;
import com.julys.eccomerce.eccomerce.service.product.ProductService;

@Component
public class CategoryProductServiceImpl implements CategoryProductService {

  @Autowired
  private ProductCategoriesDAO categoryProductDAO;

  @Autowired
  private ProductService productService;

  @Autowired
  private CategoryDAO categoryDao;

  @Override
  public ResponseEntity<?> addProductToCategory(Long categoryId, Long productId) {

    try {
      ProductCategory productCategory = new ProductCategory();
      ProductCategoryId productCategoryId = new ProductCategoryId();

      Product productFind = productService.findById(productId);

      if (productFind == null) {
        throw new NullPointerException("Product not found");
      }

      Category categoryFind = categoryDao.getCategoryById(categoryId);

      if (categoryFind == null) {
        throw new NullPointerException("Category not found");
      }

      productCategoryId.setCategoryId(categoryId);
      productCategoryId.setProductId(productId);

      productCategory.setId(productCategoryId);
      productCategory.setCategory(categoryFind);

      productCategory.setProductCategory(productFind);
      return ResponseEntity
          .ok(categoryProductDAO.createProductCategory(productCategory));
    } catch (Exception e) {
      System.out.println(e.getMessage());

      return ResponseEntity.badRequest().body(e.getMessage());
    }

  }

}