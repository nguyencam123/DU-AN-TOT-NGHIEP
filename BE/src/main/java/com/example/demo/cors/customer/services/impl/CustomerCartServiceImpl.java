package com.example.demo.cors.customer.services.impl;

import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.customer.model.request.CustomerBookingRequest;
import com.example.demo.cors.customer.model.request.CustomerCartRequest;
import com.example.demo.cors.customer.repository.CustomerCartRepository;
import com.example.demo.cors.customer.repository.CustomerHomestayRepository;
import com.example.demo.cors.customer.services.CustomerCartService;
import com.example.demo.entities.Booking;
import com.example.demo.entities.Cart;
import com.example.demo.entities.Homestay;
import com.example.demo.infrastructure.contant.StatusCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerCartServiceImpl implements CustomerCartService {

    @Autowired
    private CustomerCartRepository customerCartRepository;
    @Autowired
    private CustomerHomestayRepository customerHomestayRepository;

    @Override
    public PageableObject<Cart> getAllHomestayInCart(CustomerCartRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Cart> cartPage = customerCartRepository.getAllHomestayInCart(pageable, request);
        return new PageableObject<>(cartPage);
    }

    @Override
    public Cart saveCart(CustomerCartRequest request) {
        Cart cart = new Cart();
        List<Homestay> listHomestay = new ArrayList<>();
        listHomestay.add(customerHomestayRepository.findById(request.getHomestayId()).get());
        cart.setUserId(request.getUserId());
        cart.setHomestay(listHomestay);
        cart.setStatus(StatusCart.HOAT_DONG);
        cart.setStartDate(request.getStartDate());
        cart.setEndDate(request.getEndDate());
        customerCartRepository.save(cart);
        return cart;
    }

    @Override
    public Boolean getOne(CustomerBookingRequest request) {
        List<Booking> listBooking = customerCartRepository.getOneBooking(request);
        if (listBooking.size() == 0) {
            return false;
        }
        return true;
    }

}
