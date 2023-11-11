package com.example.demo.cors.customer.services.impl;

import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.customer.model.request.CustomerBookingRequest;
import com.example.demo.cors.customer.repository.CustomerBookingRepository;
import com.example.demo.cors.customer.services.CustomerBookingService;
import com.example.demo.entities.Booking;
import com.example.demo.entities.Homestay;
import com.example.demo.infrastructure.contant.StatusBooking;
import com.example.demo.infrastructure.contant.TypeBooking;
import com.example.demo.repositories.HomestayRepository;
import com.example.demo.repositories.PromotionRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CustomerBookingServiceImpl implements CustomerBookingService {

    @Autowired
    private CustomerBookingRepository customerBookingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HomestayRepository homestayRepository;
    @Autowired
    private PromotionRepository promotionRepository;

    @Override
    public PageableObject<Booking> getBookingByUser(CustomerBookingRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Booking> res = customerBookingRepository.findByUserId(pageable, request.getUserId());
        return new PageableObject<>(res);
    }


    @Override
    public Booking createBooking(CustomerBookingRequest request) {
        Booking booking = new Booking();
        Homestay homestay = homestayRepository.findById(request.getHomestayId()).get();
        BigDecimal totalPrice = new BigDecimal(request.getTotalPrice());

        if (totalPrice != homestay.getPrice()) {
            booking.setTypeBooking(TypeBooking.DAT_COC);
        }
        booking.setTypeBooking(TypeBooking.THANH_TOAN_TRUOC);
        booking.setUser(userRepository.findById(request.getUserId()).get());
        booking.setTotalPrice(totalPrice);
        booking.setStartDate(request.getStartDate());
        booking.setEndDate(request.getEndDate());
        booking.setName(request.getName());
        booking.setEmail(request.getEmail());
        booking.setPhoneNumber(request.getPhoneNumber());
        booking.setHomestay(homestay);
        booking.setPromotion(promotionRepository.findById(request.getIdPromotion()).get());
        booking.setNote(request.getNote());
        booking.setStatus(StatusBooking.THANH_CONG);
        customerBookingRepository.save(booking);
        return booking;
    }

}
