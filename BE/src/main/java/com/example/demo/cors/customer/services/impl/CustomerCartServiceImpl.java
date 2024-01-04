package com.example.demo.cors.customer.services.impl;

import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.customer.model.request.CustomerCartRequest;
import com.example.demo.cors.customer.repository.CustomerCartDetailRepository;
import com.example.demo.cors.customer.repository.CustomerCartRepository;
import com.example.demo.cors.customer.repository.CustomerHomestayRepository;
import com.example.demo.cors.customer.services.CustomerCartService;
import com.example.demo.entities.Cart;
import com.example.demo.entities.CartDetail;
import com.example.demo.entities.Homestay;
import com.example.demo.infrastructure.contant.StatusCart;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerCartServiceImpl implements CustomerCartService {

    @Autowired
    private CustomerCartRepository customerCartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerHomestayRepository homestayRepository;
    @Autowired
    private CustomerCartDetailRepository customerCartDetailRepository;
    @Autowired
    private CustomerHomestayRepository customerHomestayRepository;

    @Override
    public PageableObject<Homestay> getAllHomestayInCart(CustomerCartRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Homestay> res = customerHomestayRepository.getAllHomestayInCart(pageable, request);
        return new PageableObject<>(res);
    }

    @Override
    public Cart addCart(CustomerCartRequest request) {
        Cart cart = customerCartRepository.findByUserId(request);
        if (cart == null) {
            Cart newCart = new Cart();
            newCart.setUser(userRepository.findById(request.getUserId()).get());
            customerCartRepository.save(cart);

            CartDetail cartDetail = new CartDetail();
            cartDetail.setCart(newCart);
            cartDetail.setStartDate(request.getStartDate());
            cartDetail.setEndDate(request.getEndDate());
            cartDetail.setStatus(StatusCart.HOAT_DONG);
            cartDetail.setHomestay(homestayRepository.findById(request.getHomestayId()).get());
            customerCartDetailRepository.save(cartDetail);
        } else {
            List<CartDetail> cartDetailList = customerCartDetailRepository.listCartDetail(cart.getId());
            if (!cartDetailList.isEmpty()) {
                boolean check = false;
                for (CartDetail newCartDetail : cartDetailList) {
                    if (newCartDetail.getHomestay().getId().contains(request.getHomestayId())) {
                        CartDetail cartDetail = customerCartDetailRepository.findById(newCartDetail.getId()).get();
                        cartDetail.setEndDate(request.getEndDate());
                        cartDetail.setStartDate(request.getStartDate());
                        customerCartDetailRepository.save(cartDetail);
                        check = true;
                        break;
                    }
                }
                if (!check) {
                    CartDetail cartDetail = new CartDetail();
                    cartDetail.setCart(cart);
                    cartDetail.setStartDate(request.getStartDate());
                    cartDetail.setEndDate(request.getEndDate());
                    cartDetail.setStatus(StatusCart.HOAT_DONG);
                    cartDetail.setHomestay(homestayRepository.findById(request.getHomestayId()).get());
                    customerCartDetailRepository.save(cartDetail);
                }
            } else {
                CartDetail cartDetail = new CartDetail();
                cartDetail.setCart(cart);
                cartDetail.setStartDate(request.getStartDate());
                cartDetail.setEndDate(request.getEndDate());
                cartDetail.setStatus(StatusCart.HOAT_DONG);
                cartDetail.setHomestay(homestayRepository.findById(request.getHomestayId()).get());
                customerCartDetailRepository.save(cartDetail);
            }
        }
        return cart;
    }

}
