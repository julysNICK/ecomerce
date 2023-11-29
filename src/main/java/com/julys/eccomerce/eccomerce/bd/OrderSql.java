package com.julys.eccomerce.eccomerce.bd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.julys.eccomerce.eccomerce.entity.Order;
import java.util.List;

@Repository
public interface OrderSql extends JpaRepository<Order, Long> {

  List<Order> findByUserOrder_Id(Long id);

}