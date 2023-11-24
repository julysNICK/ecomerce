package com.julys.eccomerce.eccomerce.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.julys.eccomerce.eccomerce.bd.OrderSql;
import com.julys.eccomerce.eccomerce.entity.Order;
import com.julys.eccomerce.eccomerce.util.Util;

@Component
public class OrderDAOImpl implements OrderDAO {
  @Autowired
  private OrderSql orderSql;

  @Override
  public Order findById(Long id) {

    Order order = orderSql.findById(id).orElse(null);

    return order;
  }

  @Override
  public Order createOrder(Order order) {
    return orderSql.save(order);
  }

  @Override
  public Order updateOrder(Long id, Order order) {
    Order orderToUpdate = orderSql.findById(id).orElse(null);

    if (orderToUpdate == null) {
      return null;
    }

    Util.copyNonNullProperties(order, orderToUpdate);

    orderSql.save(orderToUpdate);

    return orderToUpdate;
  }

  @Override
  public String deleteOrder(Long id) {
    var order = orderSql.findById(id).orElse(null);

    if (order == null) {
      return null;
    }

    orderSql.delete(order);

    return "Order deleted";
  }

}