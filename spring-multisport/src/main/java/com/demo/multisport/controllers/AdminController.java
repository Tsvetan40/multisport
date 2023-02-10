package com.demo.multisport.controllers;


import com.demo.multisport.dto.user.UserDto;
import com.demo.multisport.entities.user.User;
import com.demo.multisport.exceptions.user.UserDuplicateException;
import com.demo.multisport.exceptions.user.UserNotFoundException;
import com.demo.multisport.services.user.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("multisport/admin")
@Slf4j
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("")
    public ResponseEntity<Optional<UserDto>> manageUsersAdmin(HttpSession session) {
        UserDto user = (UserDto) session.getAttribute("user");

        log.info(session.getId());
        log.info("manage users= " + user);

        if (user == null || !user.getEmail().contains("@multisport.com")) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.OK);
        }

        return new ResponseEntity<>(Optional.of(user), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Optional<User>> allUsers(@PathVariable("id") Long id, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.FORBIDDEN);
        }

        try {
            return new ResponseEntity<>(Optional.of(this.adminService.getUserById(id)), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/users/blocking/{id}")
    public ResponseEntity<Optional<UserDto>> blockUser(@PathVariable("id") Long id) {
        return new ResponseEntity<>(adminService.blockUser(id), HttpStatus.OK);
    }

    @PostMapping("/users/unblocking/{id}")
    public ResponseEntity<Optional<UserDto>> restoreUserRights(@PathVariable("id") Long id) {
        return new ResponseEntity<>(adminService.restoreUser(id), HttpStatus.OK);
    }

    @PostMapping("/users/newadmin")
    public ResponseEntity<Optional<UserDto>> newAdmin(@RequestBody @Valid UserDto user, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.FORBIDDEN);
        }

        try {
            adminService.addAdmin(user);
            return new ResponseEntity<>(Optional.of(user), HttpStatus.CREATED);
        } catch (UserDuplicateException e) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);
        }
    }
}
