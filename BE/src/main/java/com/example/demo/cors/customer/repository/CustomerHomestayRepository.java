package com.example.demo.cors.customer.repository;

import com.example.demo.cors.customer.model.request.CustomerHomestayRequest;
import com.example.demo.entities.Homestay;
import com.example.demo.repositories.HomestayRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerHomestayRepository extends HomestayRepository {

    @Query(value = """
            SELECT a.* FROM homestay a
            LEFT JOIN detail_homestay b ON b.homestay_id = a.id
            LEFT JOIN convenient_homestay c ON c.id = b.convenient_homestay_id
            WHERE a.status = 0
            AND (a.number_person IS NULL OR a.number_person =:#{#customerHomestayRequest.numberPerson})
            AND (b.convenient_homestay_id IS NULL OR b.convenient_homestay_id LIKE :#{#customerHomestayRequest.numberPerson})
            AND (a.id NOT IN (SELECT b.homestay_id FROM booking b WHERE (start_date >=:#{#customerHomestayRequest.dateFrom} AND end_date <=:#{#customerHomestayRequest.dateTo})))
            """, nativeQuery = true)
    Page<Homestay> searchHomestay(Pageable pageable, CustomerHomestayRequest customerHomestayRequest);

    Homestay findHomestayById(String id);

    Page<Homestay> findByAddressContains(Pageable pageable, String address);

    @Query(value = """
            SELECT * FROM homestay a WHERE a.status = 0
            """, nativeQuery = true)
    Page<Homestay> getAllHomestay(Pageable pageable);

}
