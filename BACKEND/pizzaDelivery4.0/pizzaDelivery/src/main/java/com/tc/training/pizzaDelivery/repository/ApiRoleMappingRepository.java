package com.tc.training.pizzaDelivery.repository;

import com.tc.training.pizzaDelivery.enums.Role;
import com.tc.training.pizzaDelivery.model.ApiRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApiRoleMappingRepository extends JpaRepository<ApiRole, Long> {
    Optional<ApiRole> findByRole(Role role);

}
