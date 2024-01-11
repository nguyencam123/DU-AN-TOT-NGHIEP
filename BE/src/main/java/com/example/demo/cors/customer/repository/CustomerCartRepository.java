package com.example.demo.cors.customer.repository;

import com.example.demo.cors.customer.model.request.CustomerBookingRequest;
import com.example.demo.cors.customer.model.request.CustomerCartRequest;
import com.example.demo.entities.Booking;
import com.example.demo.entities.Cart;
import com.example.demo.entities.CartDetail;
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
            SELECT * FROM cart
            WHERE id_user = :#{#request.userId} 
            """, nativeQuery = true)
    Cart findByUserId(@Param("request") CustomerCartRequest request);

    @Query(value = """
            SELECT a.* FROM booking a
            WHERE (a.status = 1)
            AND (a.homestay_id =:#{#customerBookingRequest.homestayId})
            AND (:#{#customerHomestayRequest.dateFrom} > a.start_date AND a.end_date >:#{#customerHomestayRequest.dateFrom}
            OR :#{#customerHomestayRequest.dateTo} > a.start_date AND a.end_date > :#{#customerHomestayRequest.dateTo}
            OR (a.start_date >= :#{#customerHomestayRequest.dateFrom} AND a.end_date <= :#{#customerHomestayRequest.dateTo})))
            """, nativeQuery = true)
    List<Booking> getOneBooking(CustomerBookingRequest customerBookingRequest);

}
