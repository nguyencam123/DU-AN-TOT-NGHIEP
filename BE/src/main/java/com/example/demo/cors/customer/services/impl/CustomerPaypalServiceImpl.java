package com.example.demo.cors.customer.services.impl;

import com.example.demo.cors.customer.model.request.CustomerBookingRequest;
import com.example.demo.cors.customer.repository.CustomerBookingRepository;
import com.example.demo.cors.customer.repository.CustomerHomestayRepository;
import com.example.demo.cors.customer.services.CustomerPaypalService;
import com.example.demo.entities.Booking;
import com.example.demo.entities.Homestay;
import com.example.demo.entities.Promotion;
import com.example.demo.infrastructure.contant.PaymentMethod;
import com.example.demo.infrastructure.contant.StatusBooking;
import com.example.demo.infrastructure.exception.rest.RestApiException;
import com.example.demo.infrastructure.paypalconfig.PaypalConfig;
import com.example.demo.repositories.PromotionRepository;
import com.example.demo.repositories.UserRepository;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerPaypalServiceImpl implements CustomerPaypalService {

    @Autowired
    private APIContext apiContext;
    @Autowired
    private CustomerHomestayRepository homestayRepository;
    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerBookingRepository customerBookingRepository;

    @Override
    public Payment createPayment(CustomerBookingRequest customerBookingRequest) throws PayPalRESTException {
        Booking booking = new Booking();
        Homestay homestay = homestayRepository.findById(customerBookingRequest.getHomestayId()).get();
        if (homestay == null) {
            throw new RestApiException("Homestay khong ton tai!");
        }
        Promotion promotion = promotionRepository.findById(customerBookingRequest.getIdPromotion()).orElse(null);
        BigDecimal totalPrice = new BigDecimal(customerBookingRequest.getTotalPrice());
        booking.setTypeBooking(customerBookingRequest.getTypeBooking());
        booking.setUser(userRepository.findById(customerBookingRequest.getUserId()).get());
        booking.setTotalPrice(totalPrice);
        booking.setStartDate(customerBookingRequest.getStartDate());
        booking.setEndDate(customerBookingRequest.getEndDate());
        booking.setName(customerBookingRequest.getName());
        booking.setEmail(customerBookingRequest.getEmail());
        booking.setPhoneNumber(customerBookingRequest.getPhoneNumber());
        booking.setHomestay(homestay);
        booking.setPromotion(promotion);
        booking.setPaymentMethod(PaymentMethod.PAYPAL);
        booking.setNote(customerBookingRequest.getNote());
        booking.setStatus(StatusBooking.CHO_THANH_TOAN);
        booking.setNumberOfNight(customerBookingRequest.getNumberOfNight());
        customerBookingRepository.save(booking);

        Amount amount = new Amount();
        amount.setCurrency(PaypalConfig.currency);
        amount.setTotal(String.format("%.2f", convertVNDtoUSD(customerBookingRequest.getTotalPrice())));

        Transaction transaction = new Transaction();
        transaction.setDescription("Thanh toan hoa don");
        transaction.setAmount(amount);
        transaction.setReferenceId(booking.getId());

        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(PaypalConfig.method);

        Payment payment = new Payment();
        payment.setIntent("SALE");
        payment.setPayer(payer);
        payment.setTransactions(transactionList);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(PaypalConfig.cancelUrl);
        redirectUrls.setReturnUrl(PaypalConfig.successUrl + "?bookingId=" + booking.getId());
        payment.setRedirectUrls(redirectUrls);
        return payment.create(apiContext);
    }

    @Override
    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        return payment.execute(apiContext, paymentExecute);
    }

    public static double convertVNDtoUSD(double vndAmount) {
        try {
            URL url = new URL("https://open.er-api.com/v6/latest/USD");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            JsonObject jsonResponse = JsonParser.parseReader(reader).getAsJsonObject();

            // Lấy tỷ giá VND từ JSON response
            double vndRate = jsonResponse.getAsJsonObject("rates").get("VND").getAsDouble();

            reader.close();

            // Chuyển đổi số tiền từ VND sang USD
            double usdAmount = vndAmount / vndRate;

            return usdAmount;

        } catch (Exception e) {
            e.printStackTrace();
            return -1.0; // Trả về giá trị âm nếu có lỗi
        }
    }

}
