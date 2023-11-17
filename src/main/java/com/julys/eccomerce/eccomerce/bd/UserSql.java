package com.julys.eccomerce.eccomerce.bd;

import org.springframework.data.jpa.repository.JpaRepository;

import com.julys.eccomerce.eccomerce.entity.User;

public interface UserSql extends JpaRepository<User, Long> {
}