package com.julys.eccomerce.eccomerce.response;

public class OrderPure {

  private Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  private String dateOrder;

  public String getDateOrder() {
    return dateOrder;
  }

  public void setDateOrder(String dateOrder) {
    this.dateOrder = dateOrder;
  }

  private String statusOrder;

  public String getStatusOrder() {
    return statusOrder;
  }

  public void setStatusOrder(String statusOrder) {
    this.statusOrder = statusOrder;
  }

  private Long userOrderId;

  public Long getUserOrderId() {
    return userOrderId;
  }

  public void setUserOrderId(Long userOrderId) {
    this.userOrderId = userOrderId;
  }

}