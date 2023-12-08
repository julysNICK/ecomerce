package com.julys.eccomerce.eccomerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;

@Getter
@Entity
@Data
@Table(name = "product_categories")
public class ProductCategory {

  @EmbeddedId
  private ProductCategoryId id;

  @ManyToOne
  @JoinColumn(name = "product_id", insertable = false, updatable = false)
  @JsonBackReference(value = "productCategoryRef")
  private Product productCategory;

  @ManyToOne

  @JoinColumn(name = "category_id", insertable = false, updatable = false)
  @JsonBackReference(value = "categoryRef")
  private Category category;

}