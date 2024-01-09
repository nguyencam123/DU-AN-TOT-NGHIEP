package com.example.demo.cors.customer.repository;

import com.example.demo.cors.customer.model.request.CustomerBookingRequest;
import com.example.demo.entities.Booking;
import com.example.demo.repositories.BookingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerBookingRepository extends BookingRepository {

    @Query(value = """
            SELECT ROW_NUMBER() OVER(ORDER BY b.created_date DESC) AS stt, b.*
            FROM booking b
            WHERE b.[user_id] = :#{#customerBookingRequest.userId}
            AND b.[status] = 1 OR b.[status] = 0
            """, nativeQuery = true)
    Page<Booking> getBookingByUserId(Pageable pageable, CustomerBookingRequest customerBookingRequest);

    @Query(value = """
            SELECT * FROM booking a
            WHERE a.id =:bookingId AND a.status = 1
            """, nativeQuery = true)
    Optional<Booking> findByIdAndCancel(@Param("bookingId") String bookingId);

    @Query(value = """
            SELECT * FROM booking
            """, nativeQuery = true)
    List<Booking> getAllBooking();

}
