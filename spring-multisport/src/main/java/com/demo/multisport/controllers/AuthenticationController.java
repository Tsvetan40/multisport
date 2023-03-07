package com.demo.multisport.controllers;


import com.demo.multisport.dto.user.UserDto;
import com.demo.multisport.exceptions.PlanMapperException;
import com.demo.multisport.exceptions.user.UserDuplicateException;
import com.demo.multisport.exceptions.user.UserNotFoundException;
import com.demo.multisport.services.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/multisport")
@Slf4j
public class AuthenticationController {
    private final UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Optional<UserDto>> login(Authentication authentication) {

        try {
            UserDto user = userService.loginUser(authentication.getName());

            log.info("Logged info successfully");

            return new ResponseEntity<>(Optional.of(user), HttpStatus.OK);
        } catch (UserNotFoundException | PlanMapperException | UsernameNotFoundException e) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/newuser")
    public ResponseEntity<Optional<UserDto>> userRegistration(@RequestBody @Valid UserDto userDto, BindingResult error) {
        if (error.hasErrors()) {
            Optional<UserDto> emptyUser = Optional.empty();
            return new ResponseEntity<>(emptyUser, HttpStatus.UNAUTHORIZED);
        }

        try {
            userService.registerUser(userDto);
            log.info("Logged info successfully");

            return new ResponseEntity<>(Optional.of(userDto), HttpStatus.OK);
        } catch (UserDuplicateException e) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.UNAUTHORIZED);
        }

    }

    @PostMapping("")
    public ResponseEntity<Optional<UserDto>> authorizationNavigation() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (userService.checkAdminCredentials(auth)) {
            return new ResponseEntity<>(Optional.of(userService.getUserByEmail(auth.getName())), HttpStatus.OK);
        }

        return new ResponseEntity<>(Optional.empty(), HttpStatus.OK);
    }

}
