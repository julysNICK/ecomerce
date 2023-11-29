package com.julys.eccomerce.eccomerce.bd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.julys.eccomerce.eccomerce.entity.User;

@Repository
public interface UserSql extends JpaRepository<User, Long> {
  User findByUsername(String username);

  User findByEmail(String email);
}
