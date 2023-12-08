package com.julys.eccomerce.eccomerce.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class ProductCategoryId implements Serializable {

  // @GeneratedValue
  @Column(name = "product_id", nullable = false)
  private Long productId;

  // @GeneratedValue
  @Column(name = "category_id", nullable = false)
  private Long categoryId;

}