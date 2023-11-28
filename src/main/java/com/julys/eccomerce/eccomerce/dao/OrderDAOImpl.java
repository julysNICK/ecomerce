package com.julys.eccomerce.eccomerce.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.julys.eccomerce.eccomerce.bd.OrderSql;
import com.julys.eccomerce.eccomerce.bd.UserSql;
import com.julys.eccomerce.eccomerce.entity.Order;
import com.julys.eccomerce.eccomerce.entity.User;
import com.julys.eccomerce.eccomerce.error.ErrorOrder;
import com.julys.eccomerce.eccomerce.response.ListOrderWithUsers;
import com.julys.eccomerce.eccomerce.util.Util;
import java.util.List;

@Component

public class OrderDAOImpl implements OrderDAO {
  @Autowired
  private OrderSql orderSql;

  @Autowired
  private UserSql userSql;

  @Override
  public ErrorOrder findById(Long id) {
    ErrorOrder errorOrder = new ErrorOrder();
    try {
      Order order = orderSql.findById(id).orElse(null);

      if (order == null) {
        errorOrder.setErrorMessage("Order not found");
      }

      errorOrder.setOrder(order);

    } catch (Exception e) {
      errorOrder.setErrorMessage("Error finding order " + e.getMessage());
    }

    return errorOrder;
  }

  @Override
  public ErrorOrder createOrder(Order order) {
    ErrorOrder errorOrder = new ErrorOrder();

    try {
      User user = userSql.findById(order.getUserOrderId().getId()).orElse(null);

      if (user == null) {
        errorOrder.setErrorMessage("User not found");
      }
      errorOrder.setOrder(orderSql.save(order));
    } catch (Exception e) {
      if (e.getMessage().contains("getUserOrderId()")) {
        errorOrder.setErrorMessage("Error creating order, user not found");
      }
    }

    return errorOrder;
  }

  @Override
  public ErrorOrder updateOrder(Long id, Order order) {
    ErrorOrder errorOrder = new ErrorOrder();
    try {

      Order orderToUpdate = orderSql.findById(id).orElse(null);

      if (orderToUpdate == null) {
        errorOrder.setErrorMessage("Order not found");
      }

      Util.copyNonNullProperties(order, orderToUpdate);

      orderSql.save(orderToUpdate);

      errorOrder.setOrder(orderToUpdate);

    } catch (Exception e) {

      errorOrder.setErrorMessage("Error updating order " + e.getMessage());

      if (e.getMessage().contains("Target must not be null")) {
        errorOrder.setErrorMessage("Error updating order, order not found");
      }
    }

    return errorOrder;
  }

  @Override
  public String deleteOrder(Long id) {
    System.out.println("id: " + id);
    ErrorOrder errorOrder = new ErrorOrder();
    try {
      orderSql.deleteById(id);
      return "Order deleted";

    } catch (Exception e) {
      errorOrder.setErrorMessage("Error deleting order " + e.getMessage());
      return errorOrder.getErrorMessage();
    }
  }

  @Override
  public ListOrderWithUsers findOrderByUserId(Long id) {
    ListOrderWithUsers errorOrder = new ListOrderWithUsers();
    try {
      List<Order> order = orderSql.findOrderByUserOrderId(id);

      if (order == null) {
        errorOrder.setErrorMessage("Order not found");
      }

      errorOrder.setOrder(order);

    } catch (Exception e) {
      errorOrder.setErrorMessage("Error finding order " + e.getMessage());
    }

    return errorOrder;
  }
}