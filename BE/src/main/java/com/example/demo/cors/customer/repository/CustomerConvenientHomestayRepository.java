package com.example.demo.cors.customer.repository;

import com.example.demo.cors.customer.model.request.CustomerConvenientHomestayRequest;
import com.example.demo.entities.ConvenientHomestay;
import com.example.demo.repositories.ConvenientHomestayRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerConvenientHomestayRepository extends ConvenientHomestayRepository {

    @Query(value = """
            SELECT * FROM convenient_homestay a
            WHERE a.convenient_homestay_type_id =:#{#request.convenientHomestayTypeId}
            ORDER BY a.last_modified_date DESC
            """, nativeQuery = true)
    List<ConvenientHomestay> getAllConvenientHomestayType(CustomerConvenientHomestayRequest request);
}
