package com.example.demo.repositories;

import com.example.demo.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(BookingRepository.NAME)
public interface BookingRepository extends JpaRepository<Booking, String> {
    public static final String NAME = "BaseBookingRepository";
}
