package com.julys.eccomerce.eccomerce.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ProductCategoryId implements Serializable {

  @Column(name = "product_id", nullable = false)
  private Long productId;

  @Column(name = "category_id", nullable = false)
  private Long categoryId;

}