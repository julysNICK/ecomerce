package com.julys.eccomerce.eccomerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.julys.eccomerce.eccomerce.dao.OrderDAO;
import com.julys.eccomerce.eccomerce.entity.Order;
import com.julys.eccomerce.eccomerce.error.ErrorOrder;
import com.julys.eccomerce.eccomerce.response.order.ListOrderWithUsers;

@Component

public class OrderServiceImpl implements OrderService {

  @Autowired
  private OrderDAO orderDAO;

  @Override

  public Order createOrder(Order order) {

    Order orderSaved = orderDAO.createOrder(order);

    return orderSaved;

  }

  @Override
  public String deleteOrder(Long id) {
    return orderDAO.deleteOrder(id);
  }

  @Override
  public Order findById(Long id) {
    return orderDAO.findById(id);
  }

  @Override
  public Order updateOrder(Long id, Order order) {
    return orderDAO.updateOrder(id, order);
  }

  @Override
  public ListOrderWithUsers findOrderByUserId(Long id) {
    return orderDAO.findOrderByUserIdOrder(id);
  }

}