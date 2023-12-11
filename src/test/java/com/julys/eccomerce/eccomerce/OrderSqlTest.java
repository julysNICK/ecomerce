package com.julys.eccomerce.eccomerce;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.julys.eccomerce.eccomerce.bd.OrderSql;
import com.julys.eccomerce.eccomerce.entity.Order;
import com.julys.eccomerce.eccomerce.entity.User;

@SpringBootTest
/**
 * OrderSqlTest
 */
public class OrderSqlTest {

  @MockBean
  private OrderSql orderSql;

  @MockBean
  private UserSqlTest userSqlTest;

  @Test
  public void testCreateOrder() {
    Order order = new Order();
    User user = new User();

    user.setUsername("Test");
    user.setEmail("test@gmail.com");
    user.setPassword("123456");
    String dateFormat = "yyyy-MM-dd HH:mm:ss";

    DateTimeFormatter dateOrder = DateTimeFormatter.ofPattern(dateFormat);

    LocalDateTime now = LocalDateTime.parse("2020-01-01 00:00:00", dateOrder);

    order.setDateOrder(now);

    order.setStatusOrder("PENDING");

    order.setPriceTotal(100.0);

    order.setUserOrder(user);

    Mockito.when(orderSql.save(order)).thenReturn(order);

    assert (orderSql.save(order).equals(order));

  }

  @Test
  public void testUpdateOrder() {
    Order order = new Order();
    User user = new User();

    user.setUsername("Test");
    user.setEmail("test@gmail.com");
    user.setPassword("123456");
    String dateFormat = "yyyy-MM-dd HH:mm:ss";

    DateTimeFormatter dateOrder = DateTimeFormatter.ofPattern(dateFormat);

    LocalDateTime now = LocalDateTime.parse("2020-01-01 00:00:00", dateOrder);

    order.setDateOrder(now);

    order.setStatusOrder("PENDING");

    order.setPriceTotal(100.0);

    order.setUserOrder(user);

    Mockito.when(orderSql.save(order)).thenReturn(order);

    order.setStatusOrder("COMPLETED");

    assert (orderSql.save(order).equals(order));
  }

  @Test
  public void testDeleteOrder() {
    Mockito.doNothing().when(orderSql).deleteById(1L);

    orderSql.deleteById(1L);

    Mockito.verify(orderSql, Mockito.times(1)).deleteById(1L);
  }

  @Test
  public void testGetAllOrders() {
    List<Order> orders = new ArrayList<Order>();

    Order order = new Order();
    User user = new User();

    user.setUsername("Test");
    user.setEmail("test@gmail.com");
    user.setPassword("123456");
    String dateFormat = "yyyy-MM-dd HH:mm:ss";

    DateTimeFormatter dateOrder = DateTimeFormatter.ofPattern(dateFormat);

    LocalDateTime now = LocalDateTime.parse("2020-01-01 00:00:00", dateOrder);

    order.setDateOrder(now);

    order.setStatusOrder("PENDING");

    order.setPriceTotal(100.0);

    order.setUserOrder(user);

    orders.add(order);

    Mockito.when(orderSql.findAll()).thenReturn(orders);
    assertEquals(orders.size(), 1);
  }

  @Test
  public void testGetOrderById() {
    Order order = new Order();
    User user = new User();

    user.setUsername("Test");
    user.setEmail("test@gmail.com");
    user.setPassword("123456");
    String dateFormat = "yyyy-MM-dd HH:mm:ss";

    DateTimeFormatter dateOrder = DateTimeFormatter.ofPattern(dateFormat);

    LocalDateTime now = LocalDateTime.parse("2020-01-01 00:00:00", dateOrder);

    order.setDateOrder(now);

    order.setStatusOrder("PENDING");

    order.setPriceTotal(100.0);

    order.setUserOrder(user);

    Mockito.when(orderSql.findById(1L)).thenReturn(java.util.Optional.of(order));

    assertEquals(orderSql.findById(1L).get(), order);
  }

  @Test
  public void testGetOrderByUserId() {
    List<Order> orders = new ArrayList<Order>();

    Order order = new Order();
    User user = new User();

    user.setUsername("Test");
    user.setEmail("test@gmail.com");
    user.setPassword("123456");
    String dateFormat = "yyyy-MM-dd HH:mm:ss";

    DateTimeFormatter dateOrder = DateTimeFormatter.ofPattern(dateFormat);

    LocalDateTime now = LocalDateTime.parse("2020-01-01 00:00:00", dateOrder);

    order.setDateOrder(now);

    order.setStatusOrder("PENDING");

    order.setPriceTotal(100.0);

    order.setUserOrder(user);

    orders.add(order);

    Mockito.when(orderSql.findByUserOrder_Id(1L)).thenReturn(orders);

    assertEquals(orderSql.findByUserOrder_Id(1L), orders);

  }
}