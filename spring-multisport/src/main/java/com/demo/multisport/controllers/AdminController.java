package com.demo.multisport.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("multisport/manageusers")
public class AdminController {

    @GetMapping("")
    public String manageUsersAdmin(HttpSession session) {
        if (session.getAttribute("user") == null) {
            //return error and redirect somehow
        }

        return "";
    }
}
