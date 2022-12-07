package com.demo.multisport.services;

import com.demo.multisport.entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

@SpringBootTest
public class UserServiceTest {

   UserService userService;

   @Autowired
   UserServiceTest(UserService userService) {
       this.userService = userService;
   }

    @AfterEach
    void deleteTableContent() {
       userService.deleteAll();
    }

    @Test
    void saveUserTest() {
        User user1 = new User("Tsvetan", "Gabrovski", "tsvetan.email@test.com", "password12345", 22);
        User user2 = new User("Stoyan", "Gabrovski", "stoyan.email@test.com", "123456password", 23);
        userService.saveUser(user1);
        userService.saveUser(user2);

        Optional<User> extractUser1 = userService.findUserByEmail("tsvetan.email@test.com");
        Optional<User> extractUser2 = userService.findUserByEmail("stoyan.email@test.com");

        Assertions.assertTrue(extractUser1.isPresent());
        Assertions.assertTrue(extractUser2.isPresent());

        var actual = userService.count();
        Assertions.assertEquals(2, actual, "count of insertions in not 2 " + actual);

        userService.deleteAll();
        actual = userService.count();
        Assertions.assertEquals(0, actual, "count of insertions in not " + actual);
    }

    @Test
    void saveDuplicateUserTest() {
//        User user1 = new User("Tsvetan", "Gabrovski", "tsvetan.email@test.com", "password123456", 22);
//        User user2 = new User("Tsvetan", "Gabrovski", "tsvetan.email@test.com", "password123789", 22);
//
//        //for now to do
//        userService.saveUser(user1);
//        if (userService.hasUser(user2.getEmail())) {
//            return;
//        }
//        userService.saveUser(user2);
    }
}
