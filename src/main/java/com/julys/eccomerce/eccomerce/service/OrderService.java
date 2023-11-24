package com.julys.eccomerce.eccomerce.service;

import com.julys.eccomerce.eccomerce.entity.Order;

public interface OrderService {
  Order findById(Long id);

  Order createOrder(Order order);

  Order updateOrder(Long id, Order order);

  String deleteOrder(Long id);
}