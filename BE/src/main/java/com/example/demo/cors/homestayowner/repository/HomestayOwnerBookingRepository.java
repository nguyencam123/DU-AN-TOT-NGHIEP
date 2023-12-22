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

    @Query(value = "select a.*,((a.total_price) - ((a.total_price) * 11 / 100 )) AS 'TongSoTien' from booking a \n" +
            "left join  homestay b on a.homestay_id=b.id\n" +
            "left join owner_homestay c on b.owner_id=c.id\n" +
            "where c.id=:id", nativeQuery = true)
    Page<Booking> getBookingByOwnerHomestay(String id, Pageable pageable);

    @Query(value = """
            SELECT ROW_NUMBER() OVER(ORDER BY b.created_date DESC) AS stt, b.* ,((b.total_price) - ((b.total_price) * 11 / 100)) AS 'TongSoTien'
            FROM booking b
            JOIN dbo.homestay h ON b.homestay_id = h.id 
            JOIN owner_homestay c ON h.owner_id=c.id
            JOIN dbo.[user] u ON b.user_id = u.id
            WHERE c.id=:#{#request.idOwner} AND
            ( ( :#{#request.userName} IS NULL OR :#{#request.userName} LIKE '' OR u.name = :#{#request.userName})
            AND ( :#{#request.homestayName} IS NULL OR :#{#request.homestayName} LIKE '' OR h.name LIKE %:#{#request.homestayName}% )
            AND ( :#{#request.sdtUser} IS NULL OR :#{#request.sdtUser} LIKE '' OR u.phone_number = :#{#request.sdtUser} OR b.phone_number = :#{#request.sdtUser})
            AND ( :#{#request.nameBooking} IS NULL OR :#{#request.nameBooking} LIKE '' OR b.name LIKE %:#{#request.nameBooking}%)
            AND (:#{#request.statusBooking} IS NULL OR b.status = :#{#request.statusBooking}) )
            """, nativeQuery = true)
    Page<Booking> getAllBooking(@Param("request") HomestayOwnerBookingRequest request, Pageable pageable);

    @Query(value = "select count(a.id) as 'DoanhSo',SUM(a.total_price) as 'TongSoTien'  from booking a where a.homestay_id=:id", nativeQuery = true)
    HomestayOwnerStatisticalReponse getStatistical(String id);

    @Query(value = """
            SELECT
                COUNT(a.id) AS 'DoanhSo',
                (SUM(a.total_price) - (SUM(a.total_price) * 11 / 100)) AS 'TongSoTien'
            FROM
                booking a
                INNER JOIN homestay b ON a.homestay_id = b.id
                INNER JOIN owner_homestay c ON b.owner_id = c.id
            WHERE
                c.id = :#{#request.idOwnerHomestay}
                AND (
                YEAR(DATEADD(SECOND, a.created_date / 1000, '1970-01-01')) = :#{#request.year}
                AND (MONTH(DATEADD(SECOND, a.created_date / 1000, '1970-01-01')) = :#{#request.month} OR :#{#request.month} IS NULL OR :#{#request.month} LIKE '')
                AND (DAY(DATEADD(SECOND, a.created_date / 1000, '1970-01-01')) = :#{#request.date} OR :#{#request.date} IS NULL OR :#{#request.date} LIKE '')
				)
                AND a.status = 1;                 
            """, nativeQuery = true)
    HomestayOwnerStatisticalReponse getAllStatistical(HomestayOwnerStatisticalRequest request);

    @Query(value = """
            SELECT
            COUNT(a.id) AS 'DoanhSo',
            SUM(a.total_price) - (SUM(a.total_price) * 11 / 100)) AS 'TongSoTien'
            FROM
            booking a
            inner join homestay b on a.homestay_id=b.id
            inner join owner_homestay c on b.owner_id=c.id
            WHERE
            c.id = :#{#request.idOwnerHomestay}
            AND MONTH(DATEADD(SECOND, a.created_date / 1000, '1970-01-01')) = :#{#request.month}
            AND DATEPART(YEAR, CONVERT(DATETIME, DATEADD(SECOND, a.created_date / 1000, '1970-01-01'))) = :#{#request.year}
            AND a.status=1;                     
            """, nativeQuery = true)
    HomestayOwnerStatisticalReponse getAllStatisticalYear(HomestayOwnerStatisticalRequest request);
}
