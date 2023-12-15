package com.example.demo.cors.customer.services.impl;

import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.customer.model.request.CustomerBookingRequest;
import com.example.demo.cors.customer.repository.CustomerBookingRepository;
import com.example.demo.cors.customer.repository.CustomerHomestayRepository;
import com.example.demo.cors.customer.services.CustomerBookingService;
import com.example.demo.entities.Booking;
import com.example.demo.entities.Homestay;
import com.example.demo.entities.Promotion;
import com.example.demo.infrastructure.configemail.Email;
import com.example.demo.infrastructure.configemail.EmailSender;
import com.example.demo.infrastructure.contant.Message;
import com.example.demo.infrastructure.contant.StatusBooking;
import com.example.demo.infrastructure.contant.TypeBooking;
import com.example.demo.infrastructure.exception.rest.RestApiException;
import com.example.demo.repositories.PromotionRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CustomerBookingServiceImpl implements CustomerBookingService {

    @Autowired
    private CustomerBookingRepository customerBookingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerHomestayRepository homestayRepository;
    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private EmailSender emailSender;

    @Override
    public PageableObject<Booking> getBookingByUser(CustomerBookingRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Booking> res = customerBookingRepository.getBookingByUserId(pageable, request);
        return new PageableObject<>(res);
    }

    @Override
    public Booking saveBooking(CustomerBookingRequest request) {
        Booking booking = new Booking();
        Homestay homestay = homestayRepository.findById(request.getHomestayId()).get();
        Promotion promotion = promotionRepository.findById(request.getIdPromotion()).orElse(null);
        BigDecimal totalPrice = new BigDecimal(request.getTotalPrice());
        if (totalPrice != homestay.getPrice()) {
            booking.setTypeBooking(TypeBooking.DAT_COC);
        }
        booking.setTypeBooking(TypeBooking.THANH_TOAN_TRUOC);
        booking.setUser(userRepository.findById(request.getUserId()).get());
        booking.setTotalPrice(totalPrice);
        booking.setStartDate(request.getStartDate());
        booking.setEndDate(request.getEndDate());
        booking.setName(request.getName());
        booking.setEmail(request.getEmail());
        booking.setPhoneNumber(request.getPhoneNumber());
        booking.setHomestay(homestay);
        booking.setPromotion(promotion);
        booking.setNote(request.getNote());
        booking.setStatus(StatusBooking.KHONG_THANH_CONG);
        customerBookingRepository.save(booking);
        return booking;
    }

    @Override
    public Booking updateBooking(CustomerBookingRequest request) {
        Booking booking = customerBookingRepository.findById(request.getBookingId()).orElse(null);
        booking.setStatus(StatusBooking.THANH_CONG);
        return customerBookingRepository.save(booking);
    }

    private Booking findForUpdate(String id) {
        return customerBookingRepository.findByIdAndCancel(id).orElseThrow(() ->
                new RestApiException(Message.NOT_EXISTS));
    }

    @Override
    public Booking cancel(String id, CustomerBookingRequest request) {
        Booking booking = findForUpdate(id);

        booking.setNote(request.getNote());

        booking.setStatus(StatusBooking.HUY);

        Email email = new Email();
        email.setToEmail(new String[]{booking.getHomestay().getOwnerHomestay().getEmail()});
        email.setSubject("Thông báo hủy phòng");
        email.setTitleEmail("Homestay " + booking.getHomestay().getName() + " đã bị hủy");
        email.setBody("Lý do hủy: " + booking.getNote());
        emailSender.sendEmail(email.getToEmail(), email.getSubject(), email.getTitleEmail(), email.getBody());

        customerBookingRepository.save(booking);

        return booking;
    }

}
