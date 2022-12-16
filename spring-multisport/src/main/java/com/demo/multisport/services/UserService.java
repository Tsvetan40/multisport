package com.demo.multisport.services;

import com.demo.multisport.dao.UserRepository;
import com.demo.multisport.entities.User;
import com.demo.multisport.exceptions.UserDuplicateException;
import com.demo.multisport.exceptions.UserNotFoundException;
import com.demo.multisport.services.impl.PasswordHashService;
import com.demo.multisport.services.impl.SaltGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private SaltGeneratorService saltService;
    private PasswordHashService hashService;

    @Autowired
    public UserService(UserRepository userRepository, SaltGeneratorService saltService, PasswordHashService hashService) {
        this.userRepository = userRepository;
        this.saltService = saltService;
        this.hashService = hashService;
    }

    public User loginUser(String email, String password) {

        if (!hasUser(email)) {
            throw new UserNotFoundException(String.format("User with email %s and password %s not found", email, password));
        }

        String salt = userRepository.getSaltByEmail(email);

        Optional<User> loggedUser = userRepository.findUserByEmailAndPassword(email, hashService.hash(password, salt));
        if (loggedUser.isEmpty()) {
            throw new UserNotFoundException(String.format("User with email %s and password %s not found", email, password));
        }

        return loggedUser.get();
    }

    public User registerUser(User user) {
        if (hasUser(user.getEmail())) {
            throw new UserDuplicateException("user with email " + user.getEmail() + " already exist");
        }

        user.setSalt(saltService.generate());
        user.setPassword(hashService.hash(user.getPassword(), user.getSalt()));

        return userRepository.save(user);
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
