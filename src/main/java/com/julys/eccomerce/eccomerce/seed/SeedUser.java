// package com.julys.eccomerce.eccomerce.seed;

// import java.sql.Timestamp;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;

// import com.julys.eccomerce.eccomerce.bd.UserSql;
// import com.julys.eccomerce.eccomerce.entity.Role;
// import com.julys.eccomerce.eccomerce.entity.User;

// @Component
// public class SeedUser implements CommandLineRunner {

// @Autowired
// private UserSql userSql;

// @Override
// public void run(String... args) throws Exception {

// loadUser();
// }

// private void loadUser() {

// User newUser = new User();

// if (userSql.count() == 0) {
// for (int i = 0; i < 10; i++) {
// newUser.setUsername("user" + i);
// newUser.setEmail("user" + i + "@user.com");
// newUser.setRole(Role.ADMIN);
// newUser.setPassword("$2a$10$Zj6B8tN0POd3w/cre81kIO3F0DUl6EfKCRakpp6kEzPfB7fdxGQc6");
// newUser.setCreatedAt(Timestamp.valueOf("2021-09-01 00:00:00"));
// userSql.save(newUser);
// }
// }

// }

// }