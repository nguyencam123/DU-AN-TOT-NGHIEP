package com.example.demo.cors.customer.repository;

import com.example.demo.repositories.AdminRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerAdminRepository extends AdminRepository {

    boolean existsByUsername(String username);
}
