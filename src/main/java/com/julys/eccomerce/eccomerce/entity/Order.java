package com.julys.eccomerce.eccomerce.entity;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Transactional
@Data
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

  @ManyToOne(fetch = jakarta.persistence.FetchType.EAGER)
  @JoinColumn(name = "user_id_order", referencedColumnName = "id")
  @ToString.Exclude

  private User userOrder;

  public User getUserOrder() {
    return userOrder;
  }

  @OneToMany(mappedBy = "order")
  @JsonManagedReference
  private java.util.Set<ProductOrder> productOrders;

  public void setUserOrder(User userOrder) {
    this.userOrder = userOrder;
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