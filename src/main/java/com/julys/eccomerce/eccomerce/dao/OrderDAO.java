package com.julys.eccomerce.eccomerce.dao;

import com.julys.eccomerce.eccomerce.entity.Order;

public interface OrderDAO {

  Order findById(Long id);

  Order createOrder(Order order);

  Order updateOrder(Long id, Order order);

  String deleteOrder(Long id);

}