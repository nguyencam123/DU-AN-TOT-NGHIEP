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
public class AdminConvenientHomestayRequest extends PageableRequest {

    String nameType;

    String descType;

    String name;

    String desc;

}
