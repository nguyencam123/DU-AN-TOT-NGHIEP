package com.example.demo.cors.customer.repository;

import com.example.demo.cors.customer.model.response.CustomerHomestayResponse;
import com.example.demo.repositories.HomestayRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerHomestayRepository extends HomestayRepository {

    @Query(value = """
            SELECT ROW_NUMBER() OVER(ORDER BY a.created_date DESC) AS stt, a.id, a.name AS homestay_name, b.img_url AS image, MIN(d.price) AS price, c.name AS province_name, a.star\s
            FROM homestay a
            JOIN img_homestay b ON b.homestay_id = a.id
            JOIN province c ON c.id = a.province_id
            JOIN detail_homestay d ON d.homestay_id = a.id
            GROUP BY a.created_date, a.id, a.name, b.img_url, c.name, a.star
            """, nativeQuery = true)
    List<CustomerHomestayResponse> getAllHomestay();

    @Query(value = """
            SELECT ROW_NUMBER() OVER(ORDER BY a.created_date DESC) AS stt, a.id, a.name AS homestay_name, b.img_url AS image, MIN(d.price) AS price, c.name AS province_name, a.star\s
            FROM homestay a
            JOIN img_homestay b ON b.homestay_id = a.id
            JOIN province c ON c.id = a.province_id
            JOIN detail_homestay d ON d.homestay_id = a.id
            GROUP BY a.created_date, a.id, a.name, b.img_url, c.name, a.star
            """, nativeQuery = true)
    Page<CustomerHomestayResponse> getListHomestay(Pageable pageable);

}
