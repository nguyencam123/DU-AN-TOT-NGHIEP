package com.example.demo.cors.customer.services.impl;

import com.example.demo.cors.customer.repository.CustomerCartDetailRepository;
import com.example.demo.cors.customer.services.CustomerCartDetailService;
import com.example.demo.entities.CartDetail;
import com.example.demo.infrastructure.contant.StatusCart;
import com.example.demo.infrastructure.exception.rest.RestApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public Boolean cartDetailBooked() {
        List<CartDetail> cartDetailList = customerCartDetailRepository.cartDetailBooked();
        if (!cartDetailList.isEmpty()) {
            try {
                for (CartDetail cartDetail : cartDetailList) {
                    cartDetail.setStatus(StatusCart.KHONG_HOAT_DONG);
                    customerCartDetailRepository.save(cartDetail);
                }
                return true;
            } catch (Exception e) {
                throw new RestApiException("Lỗi khi cập nhật trạng thái cart detail!");
            }
        } else {
            return false;
        }
    }

    @Override
    public Boolean deleteCartByUser(String userId, String homestayId) {
        customerCartDetailRepository.deleteCartByUser(userId, homestayId);
        return true;
    }

}
