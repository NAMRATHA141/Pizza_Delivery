package com.tc.training.pizzaDelivery.controller;

import com.tc.training.pizzaDelivery.model.User;
import com.tc.training.pizzaDelivery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/Users")
public class UserController {

    @Autowired
    private UserService UserService;

    @GetMapping
    public List<User> getAllUsers() {
        return UserService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User User) {
        return UserService.createUser(User);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User User = UserService.getUserById(id);
        if (User == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(User);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return UserService.updateUser(id, updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        UserService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
