package com.julys.eccomerce.eccomerce.error;

import com.julys.eccomerce.eccomerce.entity.Order;

public class ErrorOrder {

  private Order order;

  private String errorMessage;

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }
}