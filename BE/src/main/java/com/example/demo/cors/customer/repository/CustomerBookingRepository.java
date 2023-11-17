package com.example.demo.cors.customer.repository;

import com.example.demo.cors.customer.model.request.CustomerBookingRequest;
import com.example.demo.entities.Booking;
import com.example.demo.repositories.BookingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerBookingRepository extends BookingRepository {

    Page<Booking> findByUserId(Pageable pageable, String userId);

    @Query(value = """
            SELECT * FROM booking a
            WHERE a.id =:bookingId AND a.status = 1
            """, nativeQuery = true)
    Optional<Booking> findByIdAndCancel(@Param("bookingId") String bookingId);

}
