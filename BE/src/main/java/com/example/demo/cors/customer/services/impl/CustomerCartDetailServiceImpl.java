package com.example.demo.cors.customer.services.impl;

import com.example.demo.cors.customer.repository.CustomerCartDetailRepository;
import com.example.demo.cors.customer.services.CustomerCartDetailService;
import com.example.demo.infrastructure.exception.rest.RestApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerCartDetailServiceImpl implements CustomerCartDetailService {

    @Autowired
    private CustomerCartDetailRepository customerCartDetailRepository;

    @Override
    public Boolean deleteCartDetail(String idCartDetail) {
        if (customerCartDetailRepository.findById(idCartDetail).isPresent()) {
            customerCartDetailRepository.deleteById(idCartDetail);
            return true;
        } else {
            throw new RestApiException("Cart detail khong ton tai!");
        }

    }

    @Override
    public Boolean deleteAllCartDetail(String userId) {
        customerCartDetailRepository.deleteAllCart(userId);
        return true;
    }

}
