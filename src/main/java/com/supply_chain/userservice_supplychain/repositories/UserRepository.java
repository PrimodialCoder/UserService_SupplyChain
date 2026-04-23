package com.supply_chain.userservice_supplychain.repositories;

import com.supply_chain.userservice_supplychain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //both post and put
    User save(User user);
    Optional<User> findByEmail(String email);
}
