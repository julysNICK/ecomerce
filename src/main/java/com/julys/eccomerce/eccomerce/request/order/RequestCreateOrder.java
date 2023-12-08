package com.julys.eccomerce.eccomerce.request.order;

import java.time.LocalDateTime;

public class RequestCreateOrder {

  private String dateOrder;

  private Long userOrderId;

  private String statusOrder;

  private Double priceTotal;

  public Double getPriceTotal() {
    return priceTotal;
  }

  public void setPriceTotal(Double priceTotal) {
    this.priceTotal = priceTotal;
  }

  public RequestCreateOrder() {
  }

  public String getDateOrder() {
    return dateOrder;
  }

  public void setDateOrder(String dateOrder) {
    this.dateOrder = dateOrder;
  }

  public Long getUserOrderId() {
    return userOrderId;
  }

  public void setUserOrderId(Long userOrderId) {
    this.userOrderId = userOrderId;
  }

  public String getStatusOrder() {
    return statusOrder;
  }

  public void setStatusOrder(String statusOrder) {
    this.statusOrder = statusOrder;
  }

}