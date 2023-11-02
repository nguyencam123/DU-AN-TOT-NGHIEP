package com.example.demo.repositories;

import com.example.demo.entities.Admin;
import com.example.demo.entities.OwnerHomestay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository(AdminRepository.NAME)
public interface AdminRepository extends JpaRepository<Admin, String> {
    public static final String NAME = "BaseAdminRepository";

    Optional<Admin> findByUsername(String username);
}
