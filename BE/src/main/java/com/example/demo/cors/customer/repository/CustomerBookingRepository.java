package com.example.demo.cors.customer.repository;

import com.example.demo.entities.Booking;
import com.example.demo.repositories.BookingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerBookingRepository extends BookingRepository {

    Page<Booking> findByUserId(Pageable pageable, String userId);

}
