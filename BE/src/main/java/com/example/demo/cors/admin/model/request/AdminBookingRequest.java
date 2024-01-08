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

      String id;

      String userName;

      String homestayName;

      String sdtUser;

      String nameBooking;

      Integer statusBooking;

      String adminTransactionCode;

      String cancellTransactionCode;

      Integer statusPayUser;

      Integer statusPayOwner;
}
