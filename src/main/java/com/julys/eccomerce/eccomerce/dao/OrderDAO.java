package com.julys.eccomerce.eccomerce.dao;

import com.julys.eccomerce.eccomerce.entity.Order;
import com.julys.eccomerce.eccomerce.error.ErrorOrder;
import com.julys.eccomerce.eccomerce.response.order.ListOrderWithUsers;

public interface OrderDAO {

  Order findById(Long id);

  Order createOrder(Order order);

  Order updateOrder(Long id, Order order);

  String deleteOrder(Long id);

  ListOrderWithUsers findOrderByUserIdOrder(Long id);

}