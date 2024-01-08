package com.example.demo.cors.customer.services.impl;

import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.customer.model.request.CustomerBookingRequest;
import com.example.demo.cors.customer.repository.CustomerBookingRepository;
import com.example.demo.cors.customer.services.CustomerBookingService;
import com.example.demo.entities.Booking;
import com.example.demo.infrastructure.configemail.Email;
import com.example.demo.infrastructure.configemail.EmailSender;
import com.example.demo.infrastructure.contant.Message;
import com.example.demo.infrastructure.contant.StatusBooking;
import com.example.demo.infrastructure.exception.rest.RestApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CustomerBookingServiceImpl implements CustomerBookingService {

    @Autowired
    private CustomerBookingRepository customerBookingRepository;
    @Autowired
    private EmailSender emailSender;

    @Override
    public PageableObject<Booking> getBookingByUser(CustomerBookingRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Booking> res = customerBookingRepository.getBookingByUserId(pageable, request);
        return new PageableObject<>(res);
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
        booking.setCancellationDate(LocalDate.now());
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
