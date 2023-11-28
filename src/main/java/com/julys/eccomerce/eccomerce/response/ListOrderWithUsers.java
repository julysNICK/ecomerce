package com.julys.eccomerce.eccomerce.response;

import com.julys.eccomerce.eccomerce.entity.Order;
import java.util.List;

public class ListOrderWithUsers {
  private List<Order> order;

  private String errorMessage;

  public List<Order> getOrder() {
    return order;
  }

  public void setOrder(List<Order> order) {
    this.order = order;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }
}