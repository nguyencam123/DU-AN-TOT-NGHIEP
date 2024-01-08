package com.example.demo.util;

import com.example.demo.cors.customer.repository.CustomerBookingRepository;
import com.example.demo.cors.customer.repository.CustomerCartDetailRepository;
import com.example.demo.entities.Booking;
import com.example.demo.entities.CartDetail;
import com.example.demo.infrastructure.contant.StatusBooking;
import com.example.demo.infrastructure.contant.StatusCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ScheduledUtil {

    @Autowired
    private CustomerCartDetailRepository customerCartDetailRepository;
    @Autowired
    private CustomerBookingRepository customerBookingRepository;

    @Scheduled(cron = "0 0 0 * * *")
    public void scheduledCheckStatusCart() {
        List<CartDetail> cartDetailList = customerCartDetailRepository.getAllCartDetail();
        Long currentTime = DateUtils.truncDate(new Date()).getTime() / 1000;
        for (CartDetail cartDetail : cartDetailList) {
            if (cartDetail.getEndDate() < currentTime) {
                cartDetail.setStatus(StatusCart.KHONG_HOAT_DONG);
                customerCartDetailRepository.save(cartDetail);
            }
        }
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void scheduledDeleteBooking() {
        List<Booking> bookingList = customerBookingRepository.getAllBooking();
        Long currentTime = DateUtils.truncDate(new Date()).getTime();
        for (Booking booking : bookingList) {
            if (booking.getStatus() == StatusBooking.CHO_THANH_TOAN &&
                    DateUtils.getDaysBetweenDates(booking.getCreatedDate(), currentTime) >= 2) {
                customerBookingRepository.delete(booking);
            }
        }
    }

}
