package com.demo.multisport.services;

import com.demo.multisport.dao.UserRepository;
import com.demo.multisport.entities.User;
import com.demo.multisport.exceptions.UserDuplicateException;
import com.demo.multisport.exceptions.UserNotFoundException;
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

    public Optional<User> loginUser(User user) {
        if (!hasUser(user.getEmail())) {
            throw new UserNotFoundException("no such a user");
        }

        return userRepository.findUserByEmail(user.getEmail());
    }

    public Optional<User> registerUser(User user) {
        if (hasUser(user.getEmail())) {
            throw new UserDuplicateException("user with email " + user.getEmail() + " already exist");
        }

        return Optional.of(userRepository.save(user));
    }

    private Optional<User> saveUser(User user) {
        return Optional.of(userRepository.save(user));
    }


    public void deleteAll() {
        userRepository.deleteAll();
    }

    private boolean hasUser(String email) {
        return this.userRepository.countUserByEmail(email) > 0;
    }
}
