package com.julys.eccomerce.eccomerce.entity;

import org.hibernate.annotations.DynamicUpdate;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
@DynamicUpdate
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(name = "date_order", nullable = false)
  private LocalDateTime dateOrder;

  public LocalDateTime getDateOrder() {
    return dateOrder;
  }

  public void setDateOrder(LocalDateTime dateOrder) {
    this.dateOrder = dateOrder;
  }

  @Column(name = "status_order", nullable = false, columnDefinition = "varchar(255) default 'pending'")
  private String statusOrder;

  public String getStatusOrder() {
    return statusOrder;
  }

  public void setStatusOrder(String statusOrder) {
    this.statusOrder = statusOrder;
  }

  @Column(name = "created_at", nullable = false, columnDefinition = "timestamptz default now()")
  private LocalDateTime createdAt;

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  @Column(name = "price_total", nullable = false)
  private Double priceTotal;

  public Double getPriceTotal() {
    return priceTotal;
  }

  public void setPriceTotal(Double priceTotal) {
    this.priceTotal = priceTotal;
  }
}