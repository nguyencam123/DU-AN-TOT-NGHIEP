package com.example.demo.cors.customer.services;

import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.customer.model.request.CustomerCartRequest;
import com.example.demo.entities.Cart;
import com.example.demo.entities.Homestay;

public interface CustomerCartService {

    PageableObject<Homestay> getAllHomestayInCart(CustomerCartRequest request);

    Cart addCart(CustomerCartRequest request);

}
