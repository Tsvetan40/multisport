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
import com.demo.multisport.security.JpaUserService;
import com.demo.multisport.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PlanRepository planRepository;
    private final JpaUserService jpaUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       UserMapper userMapper,
                       PlanRepository planRepository,
                       JpaUserService jpaUserService,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.planRepository = planRepository;
        this.jpaUserService = jpaUserService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserDto loginUser(String email) {
        SecurityUser securityUser = jpaUserService.loadUserByUsername(email);

        User loggedUser = securityUser.getUser();
        return userMapper.userToUserDto(loggedUser);
    }

    public User registerUser(UserDto userDto) {
        if (hasUser(userDto.getEmail())) {
            throw new UserDuplicateException("user with email " + userDto.getEmail() + " already exist");
        }

        User user = userMapper.userDtoToUser(userDto);
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

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
        User user = getUserById(id);

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

    public UserDto getUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with email " + email + " does not exists"));
        return userMapper.userToUserDto(user);
    }

    public boolean checkAdminCredentials(Authentication authentication) {
        return authentication.getAuthorities()
                .stream()
                .anyMatch(authority -> authority.getAuthority().equals("ADMIN"));
    }
}
