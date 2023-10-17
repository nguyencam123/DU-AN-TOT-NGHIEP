package com.example.demo.cors.homestayowner.model.request;

import com.example.demo.cors.common.base.PageableRequest;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HomestayownerHomestayRequest extends PageableRequest {

      private String name;

      private String address;

      private String servicePack;

      private String statusDate;

      private String statusHomestay;

      private String statusServicePack;

}
