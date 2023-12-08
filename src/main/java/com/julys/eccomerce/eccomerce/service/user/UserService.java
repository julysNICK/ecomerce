package com.julys.eccomerce.eccomerce.service.user;

import java.util.List;

import com.julys.eccomerce.eccomerce.entity.User;

public interface UserService {
  User findById(Long id);

  List<User> allUsers();

  User createUser(User user);

  User updateUser(Long id, User user);

  User deleteUser(Long id);

  User loadUserByUsername(String username);

  User findByEmail(String email);
}