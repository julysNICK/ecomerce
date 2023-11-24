package com.julys.eccomerce.eccomerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.julys.eccomerce.eccomerce.dao.OrderDAO;
import com.julys.eccomerce.eccomerce.entity.Order;

@Component
public class OrderServiceImpl implements OrderService {

  @Autowired
  private OrderDAO orderDAO;

  @Override
  public Order createOrder(Order order) {
    return orderDAO.createOrder(order);
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

}