package com.julys.eccomerce.eccomerce.dao;

import com.julys.eccomerce.eccomerce.entity.User;

import java.util.List;

public interface UserDAO {
  User findById(Long id);

  List<User> allUsers();

  User createUser(User user);

  User updateUser(Long id, User user);

  User deleteUser(Long id);
}
