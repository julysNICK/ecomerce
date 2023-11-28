package com.julys.eccomerce.eccomerce.bd;

import org.springframework.data.jpa.domain.Specification;

import com.julys.eccomerce.eccomerce.entity.Order;
import com.julys.eccomerce.eccomerce.entity.User;

public class OrderSpecifications {

  public static Specification<Order> orderWithUser(User user) {
    return (root, query, builder) -> builder.equal(root.get("user"), user);
  }

}
