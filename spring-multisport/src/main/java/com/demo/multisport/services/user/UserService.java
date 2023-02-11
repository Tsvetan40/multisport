package com.demo.multisport.services.user;

import com.demo.multisport.dao.PlanRepository;
import com.demo.multisport.dao.UserRepository;
import com.demo.multisport.dto.user.UserDto;
import com.demo.multisport.entities.Plan;
import com.demo.multisport.entities.user.Status;
import com.demo.multisport.entities.user.User;
import com.demo.multisport.exceptions.plan.NoSuchPlanException;
import com.demo.multisport.exceptions.user.UserDuplicateException;
import com.demo.multisport.exceptions.user.UserNotFoundException;
import com.demo.multisport.mapper.impl.UserMapper;
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
    private final PlanRepository planRepository;

    @Autowired
    public UserService(UserRepository userRepository,
                       SaltGeneratorService saltService,
                       PasswordHashService hashService,
                       UserMapper userMapper,
                       PlanRepository planRepository) {
        this.userRepository = userRepository;
        this.saltService = saltService;
        this.hashService = hashService;
        this.userMapper = userMapper;
        this.planRepository = planRepository;
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

    private boolean hasUser(String email) {
        return this.userRepository.countUserByEmail(email) > 0;
    }

    public void enrollPlan(UserDto user, String planName) {
        User planUser = userRepository.findUserByEmail(user.getEmail())
                .orElseThrow(() -> new UserNotFoundException("No user for email" + user.getEmail()));
        Plan dbPlan = planRepository.getPlanByName(planName)
                .orElseThrow(() -> new NoSuchPlanException("No such plan " + planName));

        planUser.setPlan(dbPlan);
        userRepository.save(planUser);
    }

    public User getUserById(Long id) {
        return this.userRepository.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + "not found"));
    }

    private Optional<UserDto> changeStatusUser(Long id, Status status) {
        User user = userRepository.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + "not found"));

        user.setStatus(status);
        userRepository.save(user);

        return Optional.of(userMapper.userToUserDto(user));
    }

    public Optional<UserDto> blockUser(Long id) {
        return changeStatusUser(id, Status.BLOCKED);
    }

    public Optional<UserDto> restoreUserRights(Long id) {
        return changeStatusUser(id, Status.ACTIVE);
    }
}
