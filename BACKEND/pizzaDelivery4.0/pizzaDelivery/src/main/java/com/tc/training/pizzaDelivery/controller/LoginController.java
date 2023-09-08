package com.tc.training.pizzaDelivery.controller;

import com.google.firebase.auth.FirebaseAuthException;
import com.tc.training.pizzaDelivery.dto.UserDto;
import com.tc.training.pizzaDelivery.enums.Role;
import com.tc.training.pizzaDelivery.model.User;
import com.tc.training.pizzaDelivery.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;


@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/api/users")
public class LoginController {

    @Autowired
    private final UserService userService;
    private final UserDto userDto;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/signup")
    public User createUser(@RequestBody User User) throws FirebaseAuthException {
        return userService.createUser(User);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User User = userService.getUserById(id);
        if (User == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(User);
    }

    @PutMapping("/profile/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }

    @DeleteMapping("/profile/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/signin")
    public ResponseEntity<Object> getUserRoleByEmail(@RequestBody User user) {
        User user1 = userService.getUserByEmail(user.getEmail());
//        userDto.setEmail(user1.getEmail());
//        userDto.setId(user1.getId());
//        userDto.setName(user1.getName());
//        userDto.setAddress(user1.getAddress());
//        userDto.setPhoneNumber(user1.getPhoneNumber());

        if (user1.getRole() != null) {
            return ResponseEntity.ok(userDto);
        }

        else {
            String errorMessage = "User not found.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }
}
