package com.example.demo.cors.homestayowner.repository;


import com.example.demo.repositories.AdminRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomestayOwnerAdminRepository extends AdminRepository {

    boolean existsByUsername(String username);
}
