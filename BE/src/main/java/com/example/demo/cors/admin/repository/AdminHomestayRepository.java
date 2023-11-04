package com.example.demo.cors.admin.repository;

import com.example.demo.cors.admin.model.request.AdminHomestayRequest;
import com.example.demo.cors.admin.model.response.AdminHomestayResponse;
import com.example.demo.entities.Homestay;
import com.example.demo.repositories.HomestayRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminHomestayRepository extends HomestayRepository {

    Optional<Homestay> findById(String id);

    @Query(value = """
            SELECT dbo.homestay.name, dbo.address.name AS [address], dbo.owner_homestay.name AS [name_homestay], dbo.owner_homestay.phone_number as [phone_number], dbo.owner_homestay.email as [email]
            FROM dbo.address 
            INNER JOIN dbo.homestay ON dbo.address.id = dbo.homestay.address_id 
            INNER JOIN dbo.owner_homestay ON dbo.homestay.owner_id = dbo.owner_homestay.id
            """, nativeQuery = true)
    Page<AdminHomestayResponse> getAll(Pageable pageable, @Param("request") AdminHomestayRequest request);

}
