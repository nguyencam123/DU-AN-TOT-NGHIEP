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
            SELECT  a.id,
                    a.name,
                    a.address,
                    a.price,
                    a.start_date AS startDate,
                    a.number_person AS numberPerson,
                    a.status,
                    imageUrls.imgUrls AS imageUrls,
                    b.name AS [name_homestay],
                    b.phone_number as [phone_number],
                    b.email as [email]
                          FROM homestay a
                          INNER JOIN dbo.owner_homestay b ON a.owner_id = b.id
                          CROSS APPLY (
                              SELECT STRING_AGG(img_url, ', ') AS imgUrls
                              FROM img_homestay AS d
                              WHERE d.homestay_id = a.id
                          ) AS imageUrls
                    WHERE a.status = 1
            """,nativeQuery = true)
    Page<AdminHomestayResponse> getAllChoDuyet(Pageable pageable, @Param("request") AdminHomestayRequest request);

    @Query(value = """
            SELECT  a.id,
                    a.name,
                    a.address,
                    a.price,
                    a.start_date AS startDate,
                    a.number_person AS numberPerson,
                    a.status,
                    imageUrls.imgUrls AS imageUrls,
                    b.name AS [name_homestay],
                    b.phone_number as [phone_number],
                    b.email as [email]
                          FROM homestay a
                          INNER JOIN dbo.owner_homestay b ON a.owner_id = b.id
                          CROSS APPLY (
                              SELECT STRING_AGG(img_url, ', ') AS imgUrls
                              FROM img_homestay AS d
                              WHERE d.homestay_id = a.id
                          ) AS imageUrls
                    WHERE a.status = 0
            
            """,nativeQuery = true)
    Page<AdminHomestayResponse> getAllDaDuyet(Pageable pageable, @Param("request") AdminHomestayRequest request);

    @Query(value = """
            SELECT  a.id,
                    a.name,
                    a.address,
                    a.price,
                    a.start_date AS startDate,
                    a.number_person AS numberPerson,
                    a.status,
                    imageUrls.imgUrls AS imageUrls,
                    b.name AS [name_homestay],
                    b.phone_number as [phone_number],
                    b.email as [email]
            FROM homestay a
            INNER JOIN dbo.owner_homestay b ON a.owner_id = b.id 
            CROSS APPLY (
                SELECT STRING_AGG(img_url, ', ') AS imgUrls
                FROM img_homestay AS d
                WHERE d.homestay_id = a.id
            ) AS imageUrls
            WHERE (a.id = :#{#request.homestayId})
            """,nativeQuery = true)
    Page<AdminHomestayResponse> getAllByID(Pageable pageable, @Param("request") AdminHomestayRequest request);
}
