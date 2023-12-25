package com.example.demo.cors.customer.services;

import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.customer.model.request.CustomerBookingRequest;
import com.example.demo.cors.customer.model.request.CustomerCartRequest;
import com.example.demo.entities.Cart;

public interface CustomerCartService {

    PageableObject<Cart> getAllHomestayInCart(CustomerCartRequest request);

    Cart saveCart(CustomerCartRequest request);

    Boolean getOne(CustomerBookingRequest request);

}
