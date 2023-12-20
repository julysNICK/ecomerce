package com.julys.eccomerce.eccomerce.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.julys.eccomerce.eccomerce.entity.Order;
import com.julys.eccomerce.eccomerce.request.order.RequestCreateOrder;
import com.julys.eccomerce.eccomerce.service.order.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
  private OrderService orderService;

  @Autowired
  public OrderController(OrderService orderService) {
    this.orderService = orderService;

  }

  @GetMapping("/hello")
  public String hello() {
    return "Hello World Order";
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@PathVariable Long id) {

    return orderService.findById(id);

  }

  @PostMapping("/create")
  public ResponseEntity<?> createOrder(@RequestBody RequestCreateOrder order) {

    return orderService.createOrder(order);

  }

  @PatchMapping("/{id}")
  public ResponseEntity<?> updateOrder(@RequestBody Order order) {

    return orderService.updateOrder(order.getId(), order);

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
    return orderService.deleteOrder(id);

  }

  @GetMapping("/user/{id}")
  public ResponseEntity<?> findOrderByUserId(@PathVariable Long id) {
    return orderService.findOrderByUserId(id);

  }
}