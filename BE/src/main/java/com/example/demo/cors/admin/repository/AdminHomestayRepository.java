package com.example.demo.cors.admin.repository;

import com.example.demo.cors.admin.model.response.AdminHomestayResponse;
import com.example.demo.cors.admin.model.request.AdminHomestayRequest;
import com.example.demo.repositories.HomestayRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminHomestayRepository extends HomestayRepository {

    @Query(value = """
            SELECT dbo.homestay.name, dbo.homestay.address, dbo.owner_homestay.name AS [name_homestay], dbo.owner_homestay.phone_number as [phone_number], dbo.owner_homestay.email as [email],dbo.homestay.status
            FROM dbo.homestay
            INNER JOIN dbo.owner_homestay ON dbo.homestay.owner_id = dbo.owner_homestay.id
            WHERE dbo.homestay.status = 1
            """,nativeQuery = true)
    Page<AdminHomestayResponse> getAllChoDuyet(Pageable pageable, @Param("request") AdminHomestayRequest request);

    @Query(value = """
            SELECT dbo.homestay.name, dbo.homestay.address, dbo.owner_homestay.name AS [name_homestay], dbo.owner_homestay.phone_number as [phone_number], dbo.owner_homestay.email as [email],dbo.homestay.status
            FROM dbo.homestay
            INNER JOIN dbo.owner_homestay ON dbo.homestay.owner_id = dbo.owner_homestay.id
            WHERE dbo.homestay.status = 0
            """,nativeQuery = true)
    Page<AdminHomestayResponse> getAllDaDuyet(Pageable pageable, @Param("request") AdminHomestayRequest request);

    @Query(value = """
            SELECT dbo.homestay.name, dbo.homestay.address, dbo.owner_homestay.name AS [name_homestay], dbo.owner_homestay.phone_number as [phone_number], dbo.owner_homestay.email as [email],dbo.homestay.status
            FROM dbo.homestay
            INNER JOIN dbo.owner_homestay ON dbo.homestay.owner_id = dbo.owner_homestay.id 
            WHERE (dbo.homestay.id = :#{#request.homestayId})
            """,nativeQuery = true)
    Page<AdminHomestayResponse> getAllByID(Pageable pageable, @Param("request") AdminHomestayRequest request);
}
