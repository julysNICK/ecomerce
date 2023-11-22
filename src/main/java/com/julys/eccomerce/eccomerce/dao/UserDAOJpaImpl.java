package com.julys.eccomerce.eccomerce.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.julys.eccomerce.eccomerce.bd.UserSql;
import com.julys.eccomerce.eccomerce.entity.User;
import com.julys.eccomerce.eccomerce.util.Util;

@Component
public class UserDAOJpaImpl implements UserDAO {

  @Autowired
  private UserSql userSql;

  @Override
  public User findById(Long id) {

    User user = userSql.findById(id).orElse(null);
    return user;
  }

  @Override
  public List<User> allUsers() {

    List<User> users = userSql.findAll();

    return users;
  }

  @Override
  public User createUser(User user) {

    User userCreated = userSql.save(user);

    return userCreated;
  }

  @Override
  public User updateUser(Long id, User user) {

    var userUpdated = userSql.findById(id).orElse(null);

    if (userUpdated == null) {
      return null;
    }

    Util.copyNonNullProperties(user, userUpdated);

    userSql.save(userUpdated);

    return userUpdated;
  }

  @Override
  public User deleteUser(Long id) {
    var userToDelete = userSql.findById(id).orElse(null);
    if (userToDelete == null) {
      return null;
    }
    userSql.delete(userToDelete);
    return userToDelete;
  }

  @Override
  public User loadUserByUsername(String username) {
    // TODO Auto-generated method stub
    return userSql.findByUsername(username);
  }

  @Override
  public User findByEmail(String email) {
    // TODO Auto-generated method stub
    return userSql.findByEmail(email);
  }

}
