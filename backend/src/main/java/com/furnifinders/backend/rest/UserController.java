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

        @GetMapping
        public List<User> getAllUser() {
            return userService.getAllUser();
        }

        @GetMapping("/{id}")
        public ResponseEntity<User> getUser(@PathVariable int id) {
            User user = userService.getUser(id);
            if(user != null) {
                return ResponseEntity.ok(user);
            }
            else {
                return ResponseEntity.notFound().build();
            }
        }

        @PostMapping
        public ResponseEntity<User> addUser(@RequestBody User user) {
            user.setId(0);
            user = userService.addUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }

        @PutMapping
        public ResponseEntity<User> updateUser(@RequestBody User user) {
            User existingUser = userService.getUser(user.getId());
            if(existingUser != null) {
                userService.updateUser(user);
                return ResponseEntity.ok(user);
            }
            else {
                return ResponseEntity.notFound().build();
            }
        }

}
