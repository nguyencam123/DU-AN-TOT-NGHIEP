package com.example.demo.cors.customer.services;

import com.example.demo.cors.customer.model.request.CustomerCartRequest;

public interface CustomerCartDetailService {

    Boolean deleteCartDetail(String idCartDetail);

    Boolean deleteAllCartDetail(String userId);

    Boolean cartDetailBooked();

    Boolean deleteCartByUser(CustomerCartRequest request);

}
