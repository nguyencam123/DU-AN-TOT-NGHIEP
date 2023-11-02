package com.example.demo.cors.customer.repository;

import com.example.demo.cors.customer.model.request.CustomerHomestayRequest;
import com.example.demo.cors.customer.model.response.CustomerHomestayResponse;
import com.example.demo.entities.Homestay;
import com.example.demo.repositories.HomestayRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerHomestayRepository extends HomestayRepository {

    @Query(value = """
            SELECT a.id, a.name, a.address, a.price, a.number_person AS numberPerson, b.name AS province, c.name AS region, a.status, imageUrls.imgUrls AS imageUrls
            FROM homestay a
            INNER JOIN province b ON b.id = a.province_id
            INNER JOIN region c ON a.region_id = c.id
            CROSS APPLY (
            SELECT STRING_AGG(img_url, ', ') AS imgUrls
            FROM img_homestay d
            WHERE d.homestay_id = a.id) AS imageUrls
             """, nativeQuery = true)
    Page<CustomerHomestayResponse> getListHomestay(Pageable pageable);

    @Query(value = """
            SELECT * FROM homestay a
            INNER JOIN province b ON b.id = a.province_id
            INNER JOIN region c ON a.region_id = c.id
            INNER JOIN detail_homestay d ON d.homestay_id = a.id
            INNER JOIN convenient_homestay e ON e.id = d.convenient_homestay_id
            INNER JOIN img_homestay f ON f.homestay_id = a.id
            WHERE e.id =:#{#customerHomestayRequest.convenientId}
            """, nativeQuery = true)
    Page<Homestay> getHomestayByConvenientId(Pageable pageable, CustomerHomestayRequest customerHomestayRequest);

    @Query(value = """
            SELECT a.id, a.name, a.address, a.price, a.number_person AS numberPerson, b.name AS province, c.name AS region, a.status, imageUrls.imgUrls AS imageUrls
            FROM homestay a
            INNER JOIN province b ON b.id = a.province_id
            INNER JOIN region c ON a.region_id = c.id
            CROSS APPLY (
            SELECT STRING_AGG(img_url, ', ') AS imgUrls
            FROM img_homestay d
            WHERE d.homestay_id = a.id) AS imageUrls
            WHERE b.id =:#{#customerHomestayRequest.provinceId}
            """, nativeQuery = true)
    Page<CustomerHomestayResponse> getHomestayByProvince(Pageable pageable, CustomerHomestayRequest customerHomestayRequest);

    @Query(value = """
            SELECT a.id, a.name, a.address, a.price, a.number_person AS numberPerson, b.name AS province, c.name AS region, a.status, imageUrls.imgUrls AS imageUrls
            FROM homestay a
            INNER JOIN province b ON b.id = a.province_id
            INNER JOIN region c ON a.region_id = c.id
            CROSS APPLY (
            SELECT STRING_AGG(img_url, ', ') AS imgUrls
            FROM img_homestay d
            WHERE d.homestay_id = a.id) AS imageUrls
            WHERE c.id =:#{#customerHomestayRequest.regionId}
            """, nativeQuery = true)
    Page<CustomerHomestayResponse> getHomestayByRegion(Pageable pageable, CustomerHomestayRequest customerHomestayRequest);

    @Query(value = """
            SELECT ROW_NUMBER() OVER(ORDER BY a.created_date DESC) AS stt, a.id, a.name AS homestay_name, b.img_url AS image, c.name AS province_name, a.price
            FROM homestay a
            JOIN img_homestay b ON b.homestay_id = a.id
            JOIN province c ON c.id = a.province_id
            JOIN detail_homestay d ON d.homestay_id = a.id
            JOIN region e ON e.id = a.region_id
            WHERE a.id =:#{#customerHomestayRequest.homestayId}
            GROUP BY a.created_date, a.id, a.name, c.name, b.img_url, a.price
            """, nativeQuery = true)
    CustomerHomestayResponse getHomestayById(CustomerHomestayRequest customerHomestayRequest);

    Page<Homestay> findByProvinceId(Pageable pageable, String provinceId);

    Page<Homestay> findByRegionId(Pageable pageable, String regionId);

    Homestay findHomestayById(String id);

}
