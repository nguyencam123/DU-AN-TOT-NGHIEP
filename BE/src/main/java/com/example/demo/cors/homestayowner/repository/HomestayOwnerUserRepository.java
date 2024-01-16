package com.example.demo.cors.homestayowner.repository;

import com.example.demo.repositories.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomestayOwnerUserRepository extends UserRepository {

    boolean existsByUsername(String username);
}
