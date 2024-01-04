package com.example.demo.cors.customer.services;

import com.example.demo.cors.customer.model.request.CustomerCartRequest;

public interface CustomerCartDetailService {

    Boolean deleteCartDetail(CustomerCartRequest request);

    void deleteAllCartDetail(CustomerCartRequest request);

}
