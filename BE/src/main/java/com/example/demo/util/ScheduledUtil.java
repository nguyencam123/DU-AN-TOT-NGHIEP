package com.example.demo.util;

import com.example.demo.cors.customer.repository.CustomerCartDetailRepository;
import com.example.demo.entities.CartDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ScheduledUtil {

    @Autowired
    private CustomerCartDetailRepository customerCartDetailRepository;

    @Scheduled(cron = "0 0 0 * * *")
    public void scheduledCheckStatusCart() {
        List<CartDetail> cartDetailList = customerCartDetailRepository.getAllCartDetail();
        for (CartDetail cartDetail : cartDetailList) {
            Long currentTime = DateUtils.truncDate(new Date()).getTime() / 1000;
//            if(cartDetail.getEndDate()){
//
//            }
        }
    }

}
