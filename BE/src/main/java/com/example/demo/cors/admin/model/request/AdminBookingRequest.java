package com.example.demo.cors.admin.model.request;

import com.example.demo.cors.common.base.PageableRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminBookingRequest extends PageableRequest {

    private String userName;

    private String homestayName;

}
