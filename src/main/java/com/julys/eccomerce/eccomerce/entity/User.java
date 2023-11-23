package com.julys.eccomerce.eccomerce.entity;

import java.util.Collection;
import java.util.List;

import org.apache.catalina.Role;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
@DynamicUpdate
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "username", nullable = false)
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "email", nullable = false)
  private String email;

  @Enumerated(EnumType.STRING)
  @Column(name = "role", nullable = false)
  private com.julys.eccomerce.eccomerce.entity.Role role;

  public com.julys.eccomerce.eccomerce.entity.Role getRole() {
    return role;
  }

  public void setRole(com.julys.eccomerce.eccomerce.entity.Role role) {
    this.role = role;
  }

  @CreatedDate
  @Column(name = "password_changed_at", nullable = true)
  private java.sql.Timestamp passwordChangedAt;

  @CreatedDate
  @Column(name = "created_at", nullable = true)
  private java.sql.Timestamp createdAt;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  /**
   * The name of the user.
   */

  public String getUsername() {
    return email;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * The password of the user.
   */

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * The email of the user.
   */

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * The passwordChangedAt of the user.
   */

  public java.sql.Timestamp getPasswordChangedAt() {
    return passwordChangedAt;
  }

  public void setPasswordChangedAt(java.sql.Timestamp passwordChangedAt) {
    this.passwordChangedAt = passwordChangedAt;
  }

  /**
   * The createdAt of the user.
   */

  public java.sql.Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(java.sql.Timestamp createdAt) {

    this.createdAt = createdAt;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    System.out.println("getAuthorities");
    System.out.println(role.name());
    return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
  }

  @Override
  public boolean isAccountNonExpired() {
    System.out.println("isAccountNonExpired");
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    System.out.println("isAccountNonLocked");
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    System.out.println("isCredentialsNonExpired");
    return true;
  }

  @Override
  public boolean isEnabled() {
    System.out.println("isEnabled");
    return true;
  }

}