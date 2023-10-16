package com.example.demo.cors.customer.services.impl;

import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.customer.model.request.CustomerHotelRequest;
import com.example.demo.cors.customer.model.response.CustomerHotelResponse;
import com.example.demo.cors.customer.repository.CustomerHomestayRepository;
import com.example.demo.cors.customer.services.CustomerHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerHotelServiceImpl implements CustomerHotelService {

    @Autowired
    private CustomerHomestayRepository hotelRepository;

    @Override
    public PageableObject<CustomerHotelResponse> getListHotel(CustomerHotelRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<CustomerHotelResponse> res = hotelRepository.getListHotel(pageable);
        return new PageableObject<>(res);
    }

}
