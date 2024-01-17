package com.example.demo.cors.admin.repository;

import com.example.demo.cors.admin.model.request.AdminConvenientHomestayRequest;
import com.example.demo.cors.admin.model.request.AdminConvenientHomestayTypeRequest;
import com.example.demo.entities.ConvenientHomestay;
import com.example.demo.entities.ConvenientHomestayType;
import com.example.demo.repositories.ConvenientHomestayTypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminConvenientHomestayTypeRespository extends ConvenientHomestayTypeRepository {

    @Query(value = """
            SELECT ROW_NUMBER() OVER(ORDER BY cvt.last_modified_date DESC) AS stt, cvt.*
            FROM dbo.convenient_homestay_type cvt
            """,nativeQuery = true)
    Page<ConvenientHomestayType> getAllConvenientHomestayType(Pageable pageable , AdminConvenientHomestayTypeRequest request);

    boolean existsByName(String nameType);

}
