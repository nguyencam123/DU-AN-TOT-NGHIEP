package com.example.demo.cors.homestayowner.repository;

import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerStatisticalReponse;
import com.example.demo.cors.homestayowner.model.request.HomestayOwnerBookingRequest;
import com.example.demo.cors.homestayowner.model.request.HomestayOwnerStatisticalRequest;
import com.example.demo.entities.Booking;
import com.example.demo.repositories.BookingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HomestayOwnerBookingRepository extends BookingRepository {

    @Query(value = "select * from booking where homestay_id=:id", nativeQuery = true)
    Page<Booking> getBookingByHomestay(String id, Pageable pageable);

    @Query(value = """
            SELECT ROW_NUMBER() OVER(ORDER BY b.created_date DESC) AS stt, b.* 
            FROM booking b
            JOIN dbo.homestay h ON b.homestay_id = h.id 
            JOIN dbo.[user] u ON b.user_id = u.id
            WHERE ( ( :#{#request.userName} IS NULL OR :#{#request.userName} LIKE '' OR u.name = :#{#request.userName})
            AND ( :#{#request.homestayName} IS NULL OR :#{#request.homestayName} LIKE '' OR h.name LIKE %:#{#request.homestayName}% )
            AND ( :#{#request.sdtUser} IS NULL OR :#{#request.sdtUser} LIKE '' OR u.phone_number = :#{#request.sdtUser} OR b.phone_number = :#{#request.sdtUser})
            AND ( :#{#request.nameBooking} IS NULL OR :#{#request.nameBooking} LIKE '' OR b.name LIKE %:#{#request.nameBooking}%)
            AND (:#{#request.statusBooking} IS NULL OR b.status = :#{#request.statusBooking}) )
            """, nativeQuery = true)
    Page<Booking> getAllBooking(@Param("request") HomestayOwnerBookingRequest request, Pageable pageable);

    @Query(value = "select count(a.id) as 'DoanhSo',SUM(a.total_price) as 'TongSoTien'  from booking a where a.homestay_id=:id", nativeQuery = true)
    HomestayOwnerStatisticalReponse getStatistical(String id);

    @Query(value = """
            SELECT\s
            COUNT(a.id) AS 'DoanhSo',\s
            SUM(a.total_price) AS 'TongSoTien'\s
            FROM\s
            booking a\s
            inner join homestay b on a.homestay_id=b.id
            inner join owner_homestay c on b.owner_id=c.id
            WHERE\s
            c.id = :#{#request.idOwnerHomestay}
            AND (DATEPART(YEAR, CONVERT(DATETIME, DATEADD(SECOND, a.created_date / 1000, '1970-01-01'))) = :#{#request.year}
            or DATEPART(YEAR, CONVERT(DATETIME, DATEADD(SECOND, a.created_date / 1000, '1970-01-01'))) LIKE '')
            AND DATEPART(MONTH, CONVERT(DATETIME, DATEADD(SECOND, a.created_date / 1000, '1970-01-01'))) = :#{#request.month}
            AND a.status=0;                     
            """, nativeQuery = true)
    HomestayOwnerStatisticalReponse getAllStatistical(HomestayOwnerStatisticalRequest request);

    @Query(value = """
            SELECT\s
            COUNT(a.id) AS 'DoanhSo',\s
            SUM(a.total_price) AS 'TongSoTien'\s
            FROM\s
            booking a\s
            inner join homestay b on a.homestay_id=b.id
            inner join owner_homestay c on b.owner_id=c.id
            WHERE\s
            c.id = :#{#request.idOwnerHomestay}
            AND DATEPART(YEAR, CONVERT(DATETIME, DATEADD(SECOND, a.created_date / 1000, '1970-01-01'))) = :#{#request.year}
            AND a.status=0;                     
            """, nativeQuery = true)
    HomestayOwnerStatisticalReponse getAllStatisticalYear(HomestayOwnerStatisticalRequest request);
}
