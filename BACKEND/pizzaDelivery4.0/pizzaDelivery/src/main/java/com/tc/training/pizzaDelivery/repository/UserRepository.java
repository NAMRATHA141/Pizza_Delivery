package com.tc.training.pizzaDelivery.repository;

import com.tc.training.pizzaDelivery.enums.Role;
import com.tc.training.pizzaDelivery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByFirebaseId(String firebaseId);
    User findByEmail(String email);


}

