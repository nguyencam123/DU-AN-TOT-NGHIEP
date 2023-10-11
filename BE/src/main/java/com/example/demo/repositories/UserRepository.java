package com.example.demo.repositories;

import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(UserRepository.NAME)
public interface UserRepository extends JpaRepository<User, String> {
    public static final String NAME = "BaseUserRepository";

}
