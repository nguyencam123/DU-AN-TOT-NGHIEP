package com.example.demo.cors.customer.repository;

import com.example.demo.cors.customer.model.response.CustomerHotelResponse;
import com.example.demo.repositories.HotelRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface CustomerHotelRepository extends HotelRepository {

    @Query(value = """
            SELECT ROW_NUMBER() OVER(ORDER BY h.created_date DESC) AS stt, h.name, h.address, h.star FROM hotel h;
            """, nativeQuery = true)
    Page<CustomerHotelResponse> getListHotel(Pageable pageable);

}
