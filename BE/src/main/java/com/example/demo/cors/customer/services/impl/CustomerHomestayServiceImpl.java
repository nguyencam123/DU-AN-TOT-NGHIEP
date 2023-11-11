package com.example.demo.cors.customer.services.impl;

import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.customer.model.request.CustomerHomestayRequest;
import com.example.demo.cors.customer.repository.CustomerBookingRepository;
import com.example.demo.cors.customer.repository.CustomerHomestayRepository;
import com.example.demo.cors.customer.services.CustomerHomestayService;
import com.example.demo.entities.Booking;
import com.example.demo.entities.ConvenientHomestay;
import com.example.demo.entities.Homestay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerHomestayServiceImpl implements CustomerHomestayService {

    @Autowired
    private CustomerHomestayRepository customerHomestayRepository;

    @Autowired
    private CustomerBookingRepository customerBookingRepository;


    @Override
    public PageableObject<Homestay> getListHomestay(CustomerHomestayRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Homestay> res = customerHomestayRepository.getAllHomestay(pageable);
        return new PageableObject<>(res);
    }

    @Override
    public PageableObject<Homestay> searchHomestay(CustomerHomestayRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Homestay> res = customerHomestayRepository.searchHomestay(pageable, request);
        return new PageableObject<>(res);
    }

    @Override
    public Homestay getHomestayById(CustomerHomestayRequest request) {
        return customerHomestayRepository.findHomestayById(request.getHomestayId());
    }

    @Override
    public PageableObject<Homestay> getHomestayByAddress(CustomerHomestayRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Homestay> res = customerHomestayRepository.findByAddressContains(pageable, request.getAddress());
        return new PageableObject<>(res);
    }

    private String getHomestayId(Long dateFrom, Long dateTo) {
        List<Booking> bookingList = customerBookingRepository.findAll();
        for (Booking booking : bookingList) {
            if (booking.getStartDate() >= dateFrom && booking.getEndDate() <= dateTo) {
                return booking.getHomestay().getId();
            }
        }
        return null;
    }

    @Override
    public List<Homestay> search(CustomerHomestayRequest request) {
        List<Homestay> homestayList = customerHomestayRepository.listHomestay();
        List<Homestay> searchList = new ArrayList<>();

        for (Homestay homestay : homestayList
        ) {
            if (homestay.getName().contains(request.getName()) || homestay.getAddress().contains(request.getAddress())
                    && homestay.getNumberPerson() == request.getNumberPerson()
                    && homestay.getRoomNumber() == request.getRoomNumber()
                    && homestay.getId() != (getHomestayId(request.getDateFrom(), request.getDateTo()))
                    && homestay.getPrice().compareTo(request.getPriceMin()) >= 1
                    && homestay.getPrice().compareTo(request.getPriceMax()) <= -1) {
                searchList.add(homestay);
            }
        }


        return searchList;
    }

}
