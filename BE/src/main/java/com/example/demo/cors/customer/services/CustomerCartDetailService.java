package com.example.demo.cors.customer.services;

public interface CustomerCartDetailService {

    Boolean deleteCartDetail(String idCartDetail);

    Boolean deleteAllCartDetail(String userId);

    Boolean cartDetailBooked();

    Boolean deleteCartByUser(String userId, String homestayId);

}
