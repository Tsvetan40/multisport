package com.demo.multisport.controllers;


import com.demo.multisport.dto.user.UserDto;
import com.demo.multisport.services.user.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
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


//    @GetMapping("/centers")
//    public ResponseEntity<List<CenterDto>> adminGetAllCenters(HttpSession session) {
//        // to do validate session
//
//        System.out.println("HIT GetMapping centers");
//
//        return new ResponseEntity<>(adminService.getAllCenters(), HttpStatus.OK);
//    }


}
