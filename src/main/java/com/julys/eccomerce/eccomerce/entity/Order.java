package com.julys.eccomerce.eccomerce.entity;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

  @ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
  @JoinColumn(name = "user_id_order", nullable = false, referencedColumnName = "id")
  private User userOrderId;

  public User getUserOrderId() {
    return userOrderId;
  }

  public void setUserOrderId(User userOrder) {
    this.userOrderId = userOrder;
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

  @CreatedDate
  @Column(name = "created_at", nullable = false, columnDefinition = "timestamptz default now()")
  private java.sql.Timestamp createdAt;

  public java.sql.Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(java.sql.Timestamp createdAt) {
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