package com.example.demo.cors.admin.model.request;

import com.example.demo.cors.common.base.PageableRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminCommentRequest extends PageableRequest {
    String homestayId;

    String commentId;
}
