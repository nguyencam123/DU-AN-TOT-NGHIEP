package com.example.demo.cors.customer.model.request;

import com.example.demo.cors.common.base.PageableRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class CustomerBookingRequest extends PageableRequest {

    private String userId;

    private String totalPrice;

    private Long startDate;

    private Long endDate;

    private String name;

    private String email;

    private String phoneNumber;

    private String homestayId;

    private String idPromotion;

    @NotBlank(message = "Bạn phải nhập lý do hủy homestay")
    @Length(min = 20, message = "Lý do phải > 20 ký tự")
    private String note;

    private String bookingId;

    private String customerTransactionCode;

    private String adminTransactionCode;

}
