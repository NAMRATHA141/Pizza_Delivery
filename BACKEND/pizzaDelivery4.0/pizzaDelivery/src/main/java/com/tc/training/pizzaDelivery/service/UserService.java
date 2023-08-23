package com.tc.training.pizzaDelivery.service;

import com.tc.training.pizzaDelivery.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User createUser(User user);

    User updateUser(Long id, User updatedUser);

    void deleteUser(Long id);

    User getUserById(Long id);
}
