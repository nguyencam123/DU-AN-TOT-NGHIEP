package com.example.demo.cors.admin.repository;

import com.example.demo.cors.admin.model.request.AdminHomestayRequest;
import com.example.demo.entities.Homestay;
import com.example.demo.repositories.HomestayRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminHomestayRepository extends HomestayRepository {

    @Query(value = """
            SELECT ROW_NUMBER() OVER(ORDER BY h.created_date DESC) AS stt, h.* FROM homestay h 
            JOIN owner_homestay oh ON h.owner_id = oh.id
            WHERE ( ( :#{#request.statusHomestay} IS NULL OR h.status = :#{#request.statusHomestay} )
            AND ( :#{#request.nameHomestay} IS NULL OR :#{#request.nameHomestay} LIKE '' OR h.name LIKE %:#{#request.nameHomestay}% )
            AND ( :#{#request.nameOwner} IS NULL OR oh.name LIKE '' OR oh.name LIKE %:#{#request.nameOwner}% ) )
            """, nativeQuery = true)
    Page<Homestay> getAllHomestay(Pageable pageable, @Param("request") AdminHomestayRequest request);

}
