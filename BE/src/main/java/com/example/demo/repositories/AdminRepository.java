package com.example.demo.repositories;

import com.example.demo.entities.Admin;
import com.example.demo.request.AdminRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AdminRepository extends JpaRepository<Admin, UUID> {

    @Query(value = "SELECT ID, Code, Username, Password, Status FROM dbo.Admin WHERE  (Username = :#{#req.username}) and(Password = :#{#req.password})", nativeQuery = true)
    Admin getAdmin(@Param("req")AdminRequest req);

}
