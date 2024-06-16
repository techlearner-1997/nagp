package com.taskmanager.user.controller;

import com.taskmanager.user.dto.User;
import com.taskmanager.user.dto.UserResponse;
import com.taskmanager.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService service;
    @PostMapping("/updateUser")
    private ResponseEntity<User> updateUser(@RequestBody User user){
        return ResponseEntity.ok(service.updateUser(user));
    }
    @GetMapping("/getUser")
    private ResponseEntity<UserResponse> getUser(@RequestParam("email") String email){
        User user = service.getUser(email);
        UserResponse response = new UserResponse(user.getName(), user.getEmail(), user.getPhone());
        return ResponseEntity.ok(response);
    }
}
