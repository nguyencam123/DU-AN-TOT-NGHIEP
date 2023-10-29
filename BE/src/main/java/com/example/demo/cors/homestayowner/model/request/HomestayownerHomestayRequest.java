package com.example.demo.cors.homestayowner.model.request;

import com.example.demo.cors.common.base.PageableRequest;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HomestayownerHomestayRequest extends PageableRequest{

      private String name;

      private Long startDate;

      private Long endDate;

      private String desc;

      private BigDecimal price;

      private Integer numberPerson;

      private String address;

      private String province;

      private String region;

      private String status;

}
