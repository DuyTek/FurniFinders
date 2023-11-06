package com.furnifinders.backend.rest;

import com.furnifinders.backend.Entity.User;
import com.furnifinders.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
        private final UserService userService;

        @Autowired
        public UserController(UserService userService) {
            this.userService = userService;
        }

        @GetMapping("/all")
        public ResponseEntity<List<User>> getAllUser(){
            List<User> users = userService.getAllUser();
            return new ResponseEntity<>(users, HttpStatus.OK);
        }

        @PostMapping("/add")
        public ResponseEntity<User> addUser(@RequestBody User user){
            user.setId(0);
            User newUser = userService.addUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        }


}
