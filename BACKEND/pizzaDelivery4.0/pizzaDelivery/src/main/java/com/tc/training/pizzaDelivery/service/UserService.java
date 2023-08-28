package com.tc.training.pizzaDelivery.service;

import com.google.firebase.auth.FirebaseAuthException;
import com.tc.training.pizzaDelivery.enums.Role;
import com.tc.training.pizzaDelivery.model.ApiEntity;
import com.tc.training.pizzaDelivery.model.ApiRole;
import com.tc.training.pizzaDelivery.model.User;

import java.util.ArrayList;
import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User createUser(User user) throws FirebaseAuthException;

    User updateUser(Long id, User updatedUser);

    void deleteUser(Long id);

    User getUserById(Long id);

    List<ApiEntity> getAccessibleApis(Role role);

}
