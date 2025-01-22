package com.example.shike.controller;

import com.example.shike.entity.User;
import com.example.shike.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestParam String phone, @RequestParam String password) {
        return userService.register(phone, password);
    }

    @PostMapping("/login")
    public User login(@RequestParam String account, @RequestParam String password) {
        return userService.login(account, password);
    }
}
