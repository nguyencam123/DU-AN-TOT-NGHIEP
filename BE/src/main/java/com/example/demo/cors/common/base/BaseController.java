package com.example.demo.cors.common.base;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class BaseController {

    @Autowired
    private HttpSession httpSession;

}
