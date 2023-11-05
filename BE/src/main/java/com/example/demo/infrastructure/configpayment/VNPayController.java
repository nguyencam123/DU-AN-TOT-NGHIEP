package com.example.demo.infrastructure.configpayment;

import com.example.demo.cors.customer.model.request.CustomerVNPayRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
public class VNPayController {

    @Autowired
    private VNPayService vnPayService;

    @PostMapping("/submit-booking")
    public ResponseEntity<String> submidBooking(@RequestBody CustomerVNPayRequest vnPayRequest, HttpServletRequest request) {
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String vnpayUrl = vnPayService.createOrder(vnPayRequest, baseUrl);
        return ResponseEntity.ok(vnpayUrl);
    }

    @GetMapping("/vnpay")
    public ResponseEntity<String> getMapping(HttpServletRequest request) {
        int paymentStatus = vnPayService.orderReturn(request);

        String orderInfo = request.getParameter("vnp_OrderInfo");
        String paymentTime = request.getParameter("vnp_PayDate");
        String transactionId = request.getParameter("vnp_TransactionNo");
        String totalPrice = request.getParameter("vnp_Amount");

        String response = "orderId: " + orderInfo + ", totalPrice: " + totalPrice + ", paymentTime: " + paymentTime + ", transactionId: " + transactionId;
        return paymentStatus == 1 ? ResponseEntity.ok("ordersuccess - " + response) : ResponseEntity.ok("orderfail - " + response);
    }

}
