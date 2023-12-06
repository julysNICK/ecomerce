package com.julys.eccomerce.eccomerce.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Component;
import com.julys.eccomerce.eccomerce.bd.OrderSql;
import com.julys.eccomerce.eccomerce.entity.Order;
import com.julys.eccomerce.eccomerce.error.ErrorOrder;
import com.julys.eccomerce.eccomerce.response.ListOrderWithUsers;
import com.julys.eccomerce.eccomerce.util.Util;

import java.util.List;

@Component

public class OrderDAOImpl implements OrderDAO {
  @Autowired
  private OrderSql orderSql;

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
  public Order createOrder(Order order) {

    try {
      return orderSql.save(order);
    } catch (InvalidDataAccessApiUsageException e) {

      throw new InvalidDataAccessApiUsageException("Error creating order: " + e.getMessage());

    } catch (NullPointerException e) {

      throw new NullPointerException("Error creating order: " + e.getMessage());

    } catch (DataIntegrityViolationException e) {

      if (e.getMessage().contains("priceTotal")) {
        throw new DataIntegrityViolationException("Error creating order, priceTotal is required");
      } else if (e.getMessage().contains("statusOrder")) {
        throw new DataIntegrityViolationException("Error creating order, userOrder is required");
      }
      throw new DataIntegrityViolationException("Error creating order: " + e.getMessage());

    } catch (DataAccessResourceFailureException e) {

      throw new DataAccessResourceFailureException("Error creating order: " + e.getMessage());
    } catch (Exception e) {

      throw new RuntimeException("Error creating order: " + e.getMessage());
    }

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
  public ListOrderWithUsers findOrderByUserIdOrder(Long id) {
    ListOrderWithUsers errorOrder = new ListOrderWithUsers();
    try {

      List<Order> order = orderSql.findByUserOrder_Id(id);

      if (order == null) {
        errorOrder.setErrorMessage("Order not found");
      }

      errorOrder.formatJson(order);

    } catch (Exception e) {
      errorOrder.setErrorMessage("Error finding order " + e.getMessage());
    }

    return errorOrder;
  }
}