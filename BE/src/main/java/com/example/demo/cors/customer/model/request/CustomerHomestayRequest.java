package com.example.demo.cors.customer.model.request;

import com.example.demo.cors.common.base.PageableRequest;
import com.example.demo.infrastructure.contant.Status;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CustomerHomestayRequest extends PageableRequest {

    private String convenientId;

    private Integer numberPerson;

    private Double point;

    private BigDecimal priceMin;

    private BigDecimal priceMax;

    private Long startDate;

    private Long endDate;

    private String homestayId;

    private Status status;

    private Long dateFrom;

    private Long dateTo;

    private String nameOrAddress;

    private Integer roomNumber;

    private List<String> convenientHomestayList;

}
