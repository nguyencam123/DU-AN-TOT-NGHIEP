package com.example.demo.cors.customer.repository;

import com.example.demo.entities.ConvenientHomestayType;
import com.example.demo.repositories.ConvenientHomestayTypeRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerConvenientTypeRepository extends ConvenientHomestayTypeRepository {

    @Query(value = """
            SELECT * FROM convenient_homestay_type
                        """, nativeQuery = true)
    List<ConvenientHomestayType> getAll();
}
