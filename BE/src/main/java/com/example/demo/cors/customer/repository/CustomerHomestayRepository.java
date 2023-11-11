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

    @Query(value = """
            SELECT a.* FROM homestay a
            LEFT JOIN detail_homestay b ON b.homestay_id = a.id
            LEFT JOIN convenient_homestay c ON c.id = b.convenient_homestay_id
            WHERE a.status = 0
            AND (:#{#customerHomestayRequest.address} IS NULL OR :#{#customerHomestayRequest.address} LIKE '' OR a.address LIKE %:#{#customerHomestayRequest.address}%)
            AND (:#{#customerHomestayRequest.numberPerson} IS NULL OR :#{#customerHomestayRequest.numberPerson} LIKE '' OR a.number_person =:#{#customerHomestayRequest.numberPerson})
            AND (:#{#customerHomestayRequest.convenientId} IS NULL OR :#{#customerHomestayRequest.convenientId} LIKE '' OR c.id LIKE %:#{#customerHomestayRequest.convenientId}%)
            AND (a.id NOT IN (SELECT d.homestay_id FROM booking d WHERE (start_date >=:#{#customerHomestayRequest.dateFrom} AND end_date <=:#{#customerHomestayRequest.dateTo})))
            """, nativeQuery = true)
    Page<Homestay> searchHomestay(Pageable pageable, CustomerHomestayRequest customerHomestayRequest);

    Homestay findHomestayById(String id);

    Page<Homestay> findByAddressContains(Pageable pageable, String address);

    @Query(value = """
            SELECT * FROM homestay a WHERE a.status = 0
            """, nativeQuery = true)
    Page<Homestay> getAllHomestay(Pageable pageable);

    @Query(value = """
            SELECT * FROM homestay a WHERE a.status = 0
            """, nativeQuery = true)
    List<Homestay> listHomestay();

}
