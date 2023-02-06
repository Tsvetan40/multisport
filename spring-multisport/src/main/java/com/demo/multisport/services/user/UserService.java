package com.demo.multisport.services.user;

import com.demo.multisport.dao.UserRepository;
import com.demo.multisport.dto.user.UserDto;
import com.demo.multisport.entities.user.User;
import com.demo.multisport.exceptions.user.UserDuplicateException;
import com.demo.multisport.exceptions.user.UserNotFoundException;
import com.demo.multisport.mapper.UserMapper;
import com.demo.multisport.services.impl.PasswordHashService;
import com.demo.multisport.services.impl.SaltGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final SaltGeneratorService saltService;
    private final PasswordHashService hashService;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository,
                       SaltGeneratorService saltService,
                       PasswordHashService hashService,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.saltService = saltService;
        this.hashService = hashService;
        this.userMapper = userMapper;
    }

    public UserDto loginUser(String email, String password) {

        if (!hasUser(email)) {
            throw new UserNotFoundException(String.format("User with email %s and password %s not found", email, password));
        }

        String salt = userRepository.getSaltByEmail(email);

        Optional<User> loggedUser = userRepository.findUserByEmailAndPassword(email, hashService.hash(password, salt));
        if (loggedUser.isEmpty()) {
            throw new UserNotFoundException(String.format("User with email %s and password %s not found", email, password));
        }

        return userMapper.userToUserDto(loggedUser.get());

    }

    public User registerUser(UserDto userDto) {
        if (hasUser(userDto.getEmail())) {
            throw new UserDuplicateException("user with email " + userDto.getEmail() + " already exist");
        }

        User user = userMapper.userDtoToUser(userDto);

        user.setSalt(saltService.generate());
        user.setPassword(hashService.hash(user.getPassword(), user.getSalt()));

        return userRepository.save(user);
    }

    private Optional<User> saveUser(User user) {
        return Optional.of(userRepository.save(user));
    }

    public User getUserByEmail(String email) {
        try {
            return userRepository.findUserByEmail(email).get();
        } catch (Exception e) {
            throw new UserNotFoundException("User with email " + email + " not found");
        }
    }

    private boolean hasUser(String email) {
        return this.userRepository.countUserByEmail(email) > 0;
    }
}
