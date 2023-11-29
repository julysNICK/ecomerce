package com.julys.eccomerce.eccomerce.response;

import com.julys.eccomerce.eccomerce.entity.Order;
import java.util.List;
import java.util.ArrayList;

public class ListOrderWithUsers {
  private List<OrderPure> orderArray = new ArrayList<OrderPure>();

  private String errorMessage;

  public List<OrderPure> getOrder() {
    return orderArray;
  }

  public void setOrder(List<OrderPure> order) {
    this.orderArray = order;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public List<OrderPure> formatJson(List<Order> order) {
    for (Order order2 : order) {
      System.out.println("order2.getId() " + order2.getId());
      OrderPure orderPure = new OrderPure();
      orderPure.setId(order2.getId());
      orderPure.setDateOrder(order2.getDateOrder().toString());
      orderPure.setStatusOrder(order2.getStatusOrder());
      orderPure.setUserOrderId(order2.getUserOrderId().getId());
      this.orderArray.add(orderPure);
    }

    return this.orderArray;

  }
}