package com.example.demo.cors.customer.model.request;

import com.example.demo.cors.common.base.PageableRequest;
import lombok.Getter;
import lombok.Setter;

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

    private String note;

}
