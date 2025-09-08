package com.ach.controller;


import com.ach.entity.User;
import com.ach.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api") // ✅ Correctly maps all endpoints under /api
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public User createUser(@RequestBody User user) {
        System.out.println("Entered");
        return userService.saveUser(user);
    }

    @GetMapping("/listUser")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
}
