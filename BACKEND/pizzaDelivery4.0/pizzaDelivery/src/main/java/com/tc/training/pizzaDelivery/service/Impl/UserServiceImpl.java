package com.tc.training.pizzaDelivery.service.Impl;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.tc.training.pizzaDelivery.enums.Role;
import com.tc.training.pizzaDelivery.model.ApiEntity;
import com.tc.training.pizzaDelivery.model.ApiRole;
import com.tc.training.pizzaDelivery.model.User;
import com.tc.training.pizzaDelivery.repository.ApiRoleMappingRepository;
import com.tc.training.pizzaDelivery.repository.UserRepository;
import com.tc.training.pizzaDelivery.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    private ApiRoleMappingRepository apiRoleMappingRepository;
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) throws FirebaseAuthException {
        if (user.getRole() == Role.ADMIN) {
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setDisplayName(user.getName())
                    .setEmail(user.getEmail());

            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
            user.setFirebaseId(userRecord.getUid());
        }
        return userRepository.save(user);
    }

    public List<ApiEntity> getAccessibleApis(Role role) {
        List<ApiEntity> accessibleApis = new ArrayList<>();

        Optional<ApiRole> byRole = apiRoleMappingRepository.findByRole(role);
        if(byRole.isPresent()){
            return byRole.get().getApis();
        }else{
            return new ArrayList<>();
        }
    }

    public User updateUser(Long id, User updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        existingUser.setName(updatedUser.getName());

        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
