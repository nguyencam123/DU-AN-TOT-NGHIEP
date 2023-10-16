package com.example.demo.repositories;

import com.example.demo.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(AdminRepository.NAME)
public interface AdminRepository extends JpaRepository<Admin, String> {
    public static final String NAME = "BaseAdminRepository";
}
