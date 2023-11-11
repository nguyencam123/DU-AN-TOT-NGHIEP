package com.example.demo.cors.admin.repository;

import com.example.demo.cors.admin.model.response.AdminHomestayResponse;
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
            select * from homestay a
            WHERE a.status = 1
            """,nativeQuery = true)
    Page<Homestay> getAllChoDuyet(Pageable pageable, @Param("request") AdminHomestayRequest request);

    @Query(value = """
            select * from homestay a
            WHERE a.status = 0
            """,nativeQuery = true)
    Page<Homestay> getAllDaDuyet(Pageable pageable, @Param("request") AdminHomestayRequest request);

    @Query(value = """
            select * from homestay a
            WHERE a.status = 2
            """,nativeQuery = true)
    Page<Homestay> getAllDaXoa(Pageable pageable, @Param("request") AdminHomestayRequest request);

    @Query(value = """
            select * from homestay a
            WHERE a.status = 3
            """,nativeQuery = true)
    Page<Homestay> getAllKhongDuyet(Pageable pageable, @Param("request") AdminHomestayRequest request);

    @Query(value = """
            select a.* from homestay a
            WHERE (a.id = :#{#request.homestayId})
            """,nativeQuery = true)
    Page<Homestay> getAllByID(Pageable pageable, @Param("request") AdminHomestayRequest request);

    @Query(value = """
            select a.* from homestay a
            left JOIN dbo.owner_homestay o ON a.owner_id = o.id
            WHERE (a.name = :#{#request.findName} and a.status = 1)or (o.name = :#{#request.findName} and a.status = 1)
            """,nativeQuery = true)
    Page<Homestay> findByNameChoDuyet(Pageable pageable, @Param("request") AdminHomestayRequest request);


    @Query(value = """
            select a.* from homestay a
            left JOIN dbo.owner_homestay o ON a.owner_id = o.id
            WHERE (a.name = :#{#request.findName} and a.status = 0)or (o.name = :#{#request.findName} and a.status = 0)
            """,nativeQuery = true)
    Page<Homestay> findByNameDaDuyet(Pageable pageable, @Param("request") AdminHomestayRequest request);
}
