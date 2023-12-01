package com.julys.eccomerce.eccomerce.entity;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.Data;

@Entity
@DynamicUpdate
@Transactional
@Data
@Table(name = "product_orders")
public class ProductOrder {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = jakarta.persistence.FetchType.EAGER)
  @JoinColumn(name = "order_id", referencedColumnName = "id")
  @JsonIgnore
  private Order order;

  @ManyToOne(fetch = jakarta.persistence.FetchType.EAGER)
  @JoinColumn(name = "product_id", referencedColumnName = "id")
  private Product product;

  @Column(name = "quantity", nullable = false)
  private int quantity;
}