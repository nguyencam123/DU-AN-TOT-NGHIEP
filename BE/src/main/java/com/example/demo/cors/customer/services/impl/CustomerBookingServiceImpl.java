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
import com.example.demo.infrastructure.contant.StatusPayUser;
import com.example.demo.infrastructure.contant.TypeBooking;
import com.example.demo.infrastructure.exception.rest.RestApiException;
import com.example.demo.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Calendar;

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
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(booking.getCreatedDate());
        // Cộng thêm 1 ngày
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        booking.setNote(request.getNote());
        booking.setCancellationDate(LocalDate.now());
        booking.setStatus(StatusBooking.HUY);
        if (Integer.valueOf(DateUtils.getDateFromLongMilis(calendar.getTimeInMillis())) >=
                Integer.valueOf(DateUtils.getDateFromLongMilis(booking.getStartDate()))) {
            booking.setRefundPrice(new BigDecimal(0));
        } else if (booking.getTypeBooking() == TypeBooking.DAT_COC) {
            booking.setRefundPrice(new BigDecimal(0));
        } else {
            booking.setRefundPrice(booking.getTotalPrice());
            booking.setStatusPayUser(StatusPayUser.CHUA_TT_CHO_USER);
        }
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
