package com.example.demo.cors.customer.services;

import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.customer.model.request.CustomerHotelRequest;
import com.example.demo.cors.customer.model.response.CustomerHotelResponse;

public interface CustomerHotelService {

    PageableObject<CustomerHotelResponse> getListHotel(CustomerHotelRequest request);

}
