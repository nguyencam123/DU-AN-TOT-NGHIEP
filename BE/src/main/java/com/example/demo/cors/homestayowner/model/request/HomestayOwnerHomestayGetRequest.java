package com.example.demo.cors.homestayowner.model.request;

import com.example.demo.cors.common.base.PageableRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HomestayOwnerHomestayGetRequest extends PageableRequest {

    private String id;

    private String status;

}
