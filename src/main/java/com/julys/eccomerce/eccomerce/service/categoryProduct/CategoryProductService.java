package com.julys.eccomerce.eccomerce.service.categoryProduct;

import com.julys.eccomerce.eccomerce.entity.ProductCategory;

public interface CategoryProductService {
  public ProductCategory addProductToCategory(Long categoryId, Long productId);
}