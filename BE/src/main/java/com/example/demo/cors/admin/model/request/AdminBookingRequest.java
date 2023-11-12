package com.example.demo.cors.admin.model.request;

import com.example.demo.cors.common.base.PageableRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminBookingRequest extends PageableRequest {

     public String userName;

     public String homestayName;

     public String sdtUser;

     public String nameBooking;

     public Integer statusBooking;

}
