package com.example.demo.repositories;

import com.example.demo.models.Booking;
import com.example.demo.models.DetailBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailBookingRepository extends JpaRepository<DetailBooking, Booking> {
}
