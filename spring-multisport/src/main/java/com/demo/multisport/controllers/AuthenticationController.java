package com.demo.multisport.controllers;


import com.demo.multisport.dto.user.LoggedUserDto;
import com.demo.multisport.dto.user.UserDto;
import com.demo.multisport.exceptions.PlanMapperException;
import com.demo.multisport.exceptions.user.UserDuplicateException;
import com.demo.multisport.exceptions.user.UserNotFoundException;
import com.demo.multisport.services.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/multisport")
@Slf4j
public class AuthenticationController {
    private static final int MAX_INACTIVE_INTERVAL = 60*10;
    private final UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Optional<UserDto>> login(@RequestBody @Valid LoggedUserDto loggedUser,
                                                BindingResult bindingResult,
                                                HttpSession session) {
        if (bindingResult.hasErrors()) {
            Optional<UserDto> emptyUser = Optional.empty();
            return new ResponseEntity<>(emptyUser, HttpStatus.OK);
        }
        log.info("user= " + session.getAttribute("user"));

        try {
            UserDto user = userService.loginUser(loggedUser.getEmail(), loggedUser.getPassword());
            session.setAttribute("user", user);

            log.info("Logged info successfully");
            session.setMaxInactiveInterval(MAX_INACTIVE_INTERVAL);

            return new ResponseEntity<>(Optional.of(user), HttpStatus.CREATED);
        } catch (UserNotFoundException | PlanMapperException e) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/newuser")
    public ResponseEntity<Optional<UserDto>> newUserRegistration(@RequestBody @Valid UserDto userDto, BindingResult error,
                                                              HttpSession session) {
        if (error.hasErrors()) {
            Optional<UserDto> emptyUser = Optional.empty();
            return new ResponseEntity<>(emptyUser, HttpStatus.UNAUTHORIZED);
        }

        try {
            userService.registerUser(userDto);
            log.info("Logged info successfully");

            session.setAttribute("user", userDto);
            session.setMaxInactiveInterval(MAX_INACTIVE_INTERVAL);

            return new ResponseEntity<>(Optional.of(userDto), HttpStatus.OK);
        } catch (UserDuplicateException e) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.UNAUTHORIZED);
        }

    }

    @PostMapping("/logout")
    public ResponseEntity<Optional<UserDto>> logout(HttpSession session) {
        Optional<UserDto> user = Optional.empty();
        if (session.getAttribute("user") != null) {
            user = Optional.of((UserDto)session.getAttribute("user"));
        }

        session.invalidate();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Optional<UserDto>> authentication(HttpSession session) {
        log.info("Hit check if has session");
        if (session.getAttribute("user") != null) {
            return new ResponseEntity<>(Optional.of((UserDto)session.getAttribute("user")), HttpStatus.OK);
        }

        return new ResponseEntity<>(Optional.empty(), HttpStatus.OK);
    }

}
