package com.example.demo.cors.customer.repository;

import com.example.demo.cors.customer.model.request.CustomerBookingRequest;
import com.example.demo.cors.customer.model.response.CustomerBookingResponse;
import com.example.demo.repositories.BookingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerBookingRepository extends BookingRepository {

    @Query(value = """
            SELECT a.id, b.name AS user_name, c.name AS homestay_name, a.status, a.created_date, a.start_date, a.end_date, a.total_price  FROM booking a\s
            JOIN [user] b ON b.id = a.user_id
            JOIN homestay c ON c.id = a.homestay_id
            WHERE b.id =:#{#customerBookingRequest.userId}
            """, nativeQuery = true)
    Page<CustomerBookingResponse> getBookingByUser(Pageable pageable, CustomerBookingRequest customerBookingRequest);

}
