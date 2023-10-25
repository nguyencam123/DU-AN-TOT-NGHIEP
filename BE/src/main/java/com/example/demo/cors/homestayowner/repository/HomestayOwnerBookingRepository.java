package com.example.demo.cors.homestayowner.repository;

import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerBookingReponse;
import com.example.demo.repositories.BookingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HomestayOwnerBookingRepository extends BookingRepository {

    @Query(value = "select c.name as userName, b.name as homestay, a.total_price,a.start_date as startDate,a.end_date as endDate,a.status from booking a\n" +
            "inner join homestay b on a.homestay_id=b.id\n" +
            "inner join [user] c on a.user_id=c.id where b.id=:id",nativeQuery = true)
    Page<HomestayOwnerBookingReponse> getBookingByHomestay(String id, Pageable pageable);
}
