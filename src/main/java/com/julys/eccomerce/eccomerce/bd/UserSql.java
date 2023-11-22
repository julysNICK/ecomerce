package com.julys.eccomerce.eccomerce.bd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.julys.eccomerce.eccomerce.entity.User;

public interface UserSql extends JpaRepository<User, Long> {

  @Query("SELECT u FROM User u WHERE u.username = ?1")
  User findByUsername(String username);

  User findByEmail(String email);
}
