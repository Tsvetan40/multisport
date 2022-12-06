package com.demo.multisport.services;

import com.demo.multisport.dao.UserRepository;
import com.demo.multisport.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public long count() {
        return userRepository.count();
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

    public boolean hasUser(String email) {
        return this.userRepository.countUserByEmail(email) > 0;
    }
}
