package com.example.demo.cors.customer.services.impl;

import com.example.demo.cors.customer.model.request.CustomerCartRequest;
import com.example.demo.cors.customer.repository.CustomerCartDetailRepository;
import com.example.demo.cors.customer.services.CustomerCartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerCartDetailServiceImpl implements CustomerCartDetailService {

    @Autowired
    private CustomerCartDetailRepository customerCartDetailRepository;

    @Override
    public Boolean deleteCartDetail(CustomerCartRequest request) {
        customerCartDetailRepository.deleteById(request.getIdCartDetail());
        return true;
    }

    @Override
    public void deleteAllCartDetail(CustomerCartRequest request) {
        customerCartDetailRepository.deleteAllCart(request);
    }

}
