package com.example.demo.cors.customer.repository;

import com.example.demo.cors.customer.model.request.CustomerBookingRequest;
import com.example.demo.cors.customer.model.request.CustomerCartRequest;
import com.example.demo.entities.Booking;
import com.example.demo.entities.Cart;
import com.example.demo.repositories.CartRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerCartRepository extends CartRepository {

    @Query(value = """
            SELECT c.* 
            FROM cart c
            WHERE c.user_id = :#{#request.userId} 
            """, nativeQuery = true)
    Page<Cart> getAllHomestayInCart(Pageable pageable, @Param("request") CustomerCartRequest request);

    @Query(value = """
            SELECT a.* FROM booking a
            WHERE (a.status = 1)
            AND (a.homestay_id =:#{#customerBookingRequest.homestayId})
            AND ((a.start_date <=:#{#customerBookingRequest.startDate}) and (a.end_date >=:#{#customerBookingRequest.startDate}))
            AND ((a.start_date <=:#{#customerBookingRequest.endDate}) and (a.end_date >=:#{#customerBookingRequest.endDate}))
            """, nativeQuery = true)
    List<Booking> getOneBooking(CustomerBookingRequest customerBookingRequest);

}
