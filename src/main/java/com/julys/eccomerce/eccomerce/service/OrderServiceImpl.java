package com.julys.eccomerce.eccomerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.julys.eccomerce.eccomerce.dao.OrderDAO;
import com.julys.eccomerce.eccomerce.entity.Order;
import com.julys.eccomerce.eccomerce.error.ErrorOrder;
import com.julys.eccomerce.eccomerce.response.ListOrderWithUsers;

@Component
public class OrderServiceImpl implements OrderService {

  @Autowired
  private OrderDAO orderDAO;

  @Override

  public ErrorOrder createOrder(Order order) {

    return orderDAO.createOrder(order);

  }

  @Override
  public String deleteOrder(Long id) {
    return orderDAO.deleteOrder(id);
  }

  @Override
  public ErrorOrder findById(Long id) {
    return orderDAO.findById(id);
  }

  @Override
  public ErrorOrder updateOrder(Long id, Order order) {
    return orderDAO.updateOrder(id, order);
  }

  @Override
  public ListOrderWithUsers findOrderByUserId(Long id) {
    return orderDAO.findOrderByUserId(id);
  }

}