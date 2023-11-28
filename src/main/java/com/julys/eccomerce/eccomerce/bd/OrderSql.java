package com.julys.eccomerce.eccomerce.bd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.julys.eccomerce.eccomerce.entity.Order;
import com.julys.eccomerce.eccomerce.entity.User;
import java.util.List;

public interface OrderSql extends JpaRepository<Order, Long> {

  // do a join searching for the user by id along with the order
  @Query("SELECT o FROM Order o WHERE o.userOrderId.id = ?1")
  List<Order> findOrderByUserOrderId(Long id);

}