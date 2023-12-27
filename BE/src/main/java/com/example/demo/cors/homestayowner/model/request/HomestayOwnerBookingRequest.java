package com.example.demo.cors.homestayowner.model.request;

import com.example.demo.cors.common.base.PageableRequest;
import lombok.*;

@Getter
@Setter
public class HomestayOwnerBookingRequest extends PageableRequest {

    String idOwner;

    String userName;

    String homestayName;

    String sdtUser;

    String nameBooking;

    Integer statusBooking;

    Integer month;

    Integer year;

}
