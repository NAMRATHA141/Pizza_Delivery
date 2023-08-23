package com.tc.training.pizzaDelivery.service.Impl;

import com.tc.training.pizzaDelivery.model.User;
import com.tc.training.pizzaDelivery.repository.UserRepository;
import com.tc.training.pizzaDelivery.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User User) {

        return userRepository.save(User);
    }

    public User updateUser(Long id, User updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        existingUser.setUsername(updatedUser.getUsername());

        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
