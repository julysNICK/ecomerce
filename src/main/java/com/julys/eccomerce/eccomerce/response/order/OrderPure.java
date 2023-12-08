package com.julys.eccomerce.eccomerce.response.order;

import com.julys.eccomerce.eccomerce.entity.User;
import com.julys.eccomerce.eccomerce.response.user.UserResponseOrder;

public class OrderPure {

  private Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  private String dateOrder;

  public String getDateOrder() {
    return dateOrder;
  }

  public void setDateOrder(String dateOrder) {
    this.dateOrder = dateOrder;
  }

  private String statusOrder;

  public String getStatusOrder() {
    return statusOrder;
  }

  public void setStatusOrder(String statusOrder) {
    this.statusOrder = statusOrder;
  }

  private UserResponseOrder userOrderName;

  public UserResponseOrder getUserOrderName() {
    return userOrderName;
  }

  public void setUserOrderName(User userOrderName) {
    UserResponseOrder userOrderNameNew = new UserResponseOrder();

    userOrderNameNew.setName(userOrderName.getUsername());

    userOrderNameNew.setEmail(userOrderName.getEmail());

    this.userOrderName = userOrderNameNew;
  }

}