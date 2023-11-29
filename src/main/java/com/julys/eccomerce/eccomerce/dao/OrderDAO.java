package com.julys.eccomerce.eccomerce.dao;

import com.julys.eccomerce.eccomerce.entity.Order;
import com.julys.eccomerce.eccomerce.error.ErrorOrder;
import com.julys.eccomerce.eccomerce.response.ListOrderWithUsers;

public interface OrderDAO {

  // ErrorOrder findById(Long id);

  Order createOrder(Order order);

  // ErrorOrder updateOrder(Long id, Order order);

  // String deleteOrder(Long id);

  ListOrderWithUsers findOrderByUserIdOrder(Long id);

}