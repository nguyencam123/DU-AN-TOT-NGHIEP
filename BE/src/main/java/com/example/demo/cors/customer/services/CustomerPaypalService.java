package com.example.demo.cors.customer.services;

import com.example.demo.cors.customer.model.request.CustomerBookingRequest;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

public interface CustomerPaypalService {

    Payment createPayment(CustomerBookingRequest customerBookingRequest) throws PayPalRESTException;

    Payment executePayment(String paymentId, String payerId) throws PayPalRESTException;

    boolean sendBillBooking(String bookingId);

}
