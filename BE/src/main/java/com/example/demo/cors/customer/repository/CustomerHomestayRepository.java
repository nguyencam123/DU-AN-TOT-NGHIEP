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
            SELECT * FROM homestay a
            INNER JOIN detail_homestay d ON d.homestay_id = a.id
            INNER JOIN convenient_homestay e ON e.id = d.convenient_homestay_id
            INNER JOIN img_homestay f ON f.homestay_id = a.id
            WHERE e.id =:#{#customerHomestayRequest.convenientId}
            """, nativeQuery = true)
    Page<Homestay> getHomestayByConvenientId(Pageable pageable, CustomerHomestayRequest customerHomestayRequest);

    Homestay findHomestayById(String id);

}
