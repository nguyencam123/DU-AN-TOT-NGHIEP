package com.example.demo.repositories;

import com.example.demo.entities.Booking;
import com.example.demo.entities.DetailBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(DetailBookingRepository.NAME)
public interface DetailBookingRepository extends JpaRepository<DetailBooking, String> {
    public static final String NAME = "BaseDetailBookingRepository";

}
