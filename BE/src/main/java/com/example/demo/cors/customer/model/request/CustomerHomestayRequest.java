package com.example.demo.cors.customer.model.request;

import com.example.demo.cors.common.base.PageableRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerHomestayRequest extends PageableRequest {

    private String convenientId;

}
