package com.example.demo.cors.customer.repository;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Primary
@Repository

public interface CustomerLoginRepository extends UserRepository {

    @Override
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phonenumber);

    boolean existsByName(String name);

}
