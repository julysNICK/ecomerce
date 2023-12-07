package com.julys.eccomerce.eccomerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.julys.eccomerce.eccomerce.dao.ProductCategoriesDAO;
import com.julys.eccomerce.eccomerce.entity.Category;
import com.julys.eccomerce.eccomerce.entity.Product;
import com.julys.eccomerce.eccomerce.entity.ProductCategory;
import com.julys.eccomerce.eccomerce.entity.ProductCategoryId;

@Component
public class CategoryProductServiceImpl implements CategoryProductService {

  @Autowired
  private ProductCategoriesDAO categoryProductDAO;

  @Autowired
  private ProductService productService;

  @Autowired
  private CategoryService categoryService;

  @Override
  public ProductCategory addProductToCategory(Long categoryId, Long productId) {

    ProductCategory productCategory = new ProductCategory();
    ProductCategoryId productCategoryId = new ProductCategoryId();

    Product productFind = productService.findById(productId);

    if (productFind == null) {
      throw new NullPointerException("Product not found");
    }

    Category categoryFind = categoryService.getCategoryById(categoryId);

    if (categoryFind == null) {
      throw new NullPointerException("Category not found");
    }

    productCategoryId.setCategoryId(categoryId);
    productCategoryId.setProductId(productId);

    productCategory.setId(productCategoryId);
    productCategory.setCategory(categoryFind);

    productCategory.setProductCategory(productFind);

    return categoryProductDAO.createProductCategory(productCategory);
  }

}