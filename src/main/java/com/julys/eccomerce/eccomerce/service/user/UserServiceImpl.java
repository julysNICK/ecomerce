package com.julys.eccomerce.eccomerce.service.user;

import java.util.List;

import org.springframework.stereotype.Component;

import com.julys.eccomerce.eccomerce.dao.user.UserDAO;
import com.julys.eccomerce.eccomerce.entity.User;

@Component
public class UserServiceImpl implements UserService {

  private UserDAO userDAO;

  public UserServiceImpl(UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  @Override
  public User findById(Long id) {
    return userDAO.findById(id);
  }

  @Override
  public List<User> allUsers() {

    return userDAO.allUsers();
  }

  @Override
  public User createUser(User user) {

    return userDAO.createUser(user);
  }

  @Override
  public User updateUser(Long id, User user) {

    return userDAO.updateUser(id, user);
  }

  @Override
  public User deleteUser(Long id) {

    return userDAO.deleteUser(id);
  }

  @Override
  public User loadUserByUsername(String username) {

    return userDAO.loadUserByUsername(username);
  }

  @Override
  public User findByEmail(String email) {
    return userDAO.findByEmail(email);
  }

}