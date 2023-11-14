package com.example.demo.cors.customer.repository;

import com.example.demo.cors.customer.model.request.CustomerHomestayRequest;
import com.example.demo.entities.Homestay;
import com.example.demo.repositories.HomestayRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerHomestayRepository extends HomestayRepository {

    Homestay findHomestayById(String id);

    @Query(value = """
            SELECT * FROM homestay a WHERE a.status = 0
            """, nativeQuery = true)
    Page<Homestay> getAllHomestay(Pageable pageable);

    @Query(value = """
            SELECT a.* FROM homestay a
            WHERE a.status = 0
            AND a.id
            NOT IN (SELECT d.homestay_id FROM booking d WHERE (start_date >=:#{#customerHomestayRequest.dateFrom}) OR (end_date <=:#{#customerHomestayRequest.dateTo} ) AND d.status = 1)
            """, nativeQuery = true)
    List<Homestay> findAllBetweenDate(CustomerHomestayRequest customerHomestayRequest);

}
