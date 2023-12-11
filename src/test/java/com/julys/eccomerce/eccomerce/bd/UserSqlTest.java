package com.julys.eccomerce.eccomerce.bd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.julys.eccomerce.eccomerce.entity.User;

@SpringBootTest
/**
 * UserSqlTest
 */
public class UserSqlTest {

  @MockBean
  private UserSql userSql;

  private final PasswordEncoder passwordEncoder111 = new PasswordEncoder() {

    @Override
    public String encode(CharSequence rawPassword) {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
      // TODO Auto-generated method stub
      return false;
    }

    @Override
    public boolean upgradeEncoding(String encodedPassword) {
      // TODO Auto-generated method stub
      return false;
    }

  };

  @Test
  public void testCreateUser() {
    User user = new User();

    user.setUsername("Test");
    user.setEmail("test@gmail.com");
    user.setPassword(passwordEncoder111.encode("123456"));

    Mockito.when(userSql.save(user)).thenReturn(user);

    assertEquals(user.getEmail(), "test@gmail.com");

  }

  @Test
  public void testUpdateUser() {
    User user = new User();

    user.setUsername("Test");
    user.setEmail("test@gmail.com");
    user.setPassword(passwordEncoder111.encode("123456"));

    Mockito.when(userSql.save(user)).thenReturn(user);

    user.setEmail("test1@gamil.com");

    assertEquals(user.getEmail(), "test1@gamil.com");
  }
}