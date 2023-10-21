package com.example.demo.cors.customer.repository;

import com.example.demo.cors.customer.model.request.CustomerConvenientHomestayRequest;
import com.example.demo.cors.customer.model.response.CustomerConvenientHomestayResponse;
import com.example.demo.repositories.ConvenientHomestayRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerConvenientHomestayRepository extends ConvenientHomestayRepository {

    @Query(value = """
            SELECT a.name AS convenient_name, b.name AS convenient_type_name FROM convenient_homestay a
            JOIN convenient_homestay_type b ON b.id = a.convenient_homestay_type_id\s
            GROUP BY a.name, b.name
            """, nativeQuery = true)
    Page<CustomerConvenientHomestayResponse> getAllConvenient(Pageable pageable);

}
