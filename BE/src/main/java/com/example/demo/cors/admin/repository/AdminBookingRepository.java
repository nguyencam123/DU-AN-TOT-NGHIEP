package com.example.demo.cors.admin.repository;

import com.example.demo.cors.admin.model.request.AdminBookingByHomestayRequest;
import com.example.demo.cors.admin.model.request.AdminBookingRequest;
import com.example.demo.cors.admin.model.response.AdminBookingResponse;
import com.example.demo.entities.Booking;
import com.example.demo.repositories.BookingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminBookingRepository extends BookingRepository {

    @Query(value = """
                    SELECT ROW_NUMBER() OVER(ORDER BY b.created_date DESC) AS stt, b.* 
                    FROM booking b
                    JOIN dbo.homestay h ON b.homestay_id = h.id 
                    JOIN dbo.[user] u ON b.user_id = u.id
                    WHERE ( ( :#{#request.userName} IS NULL OR :#{#request.userName} LIKE '' OR u.name = :#{#request.userName})
                    AND ( :#{#request.homestayName} IS NULL OR :#{#request.homestayName} LIKE '' OR h.name LIKE %:#{#request.homestayName}% )
                    AND ( :#{#request.sdtUser} IS NULL OR :#{#request.sdtUser} LIKE '' OR u.phone_number = :#{#request.sdtUser})
                    AND ( :#{#request.nameBooking} IS NULL OR :#{#request.nameBooking} LIKE '' OR b.name LIKE %:#{#request.nameBooking}%)
                    AND (:#{#request.statusBooking} IS NULL OR b.status = :#{#request.statusBooking}) )
                    """, nativeQuery = true)
    Page<Booking> getAllBooking(@Param("request") AdminBookingRequest request, Pageable pageable);

    @Query(value = """
                    SELECT ROW_NUMBER() OVER(ORDER BY b.created_date DESC) AS stt, u.name AS user_name, b.id, b.status, b.created_date, b.start_date, b.end_date,b.total_price, h.name AS homestay_name
                    FROM booking b 
                    JOIN [user] u ON b.user_id = u.id 
                    JOIN homestay h ON b.homestay_id = h.id 
                    where h.id= :#{#req.homestayId}
                    """,nativeQuery = true)
    Page<AdminBookingResponse> getAllBookingByHomestay(@Param("req") AdminBookingByHomestayRequest req, Pageable pageable);
}
