package com.example.demo.cors.homestayowner.model.reponse;

import org.springframework.beans.factory.annotation.Value;

public interface HomestayNumberOfBookingTodayReponse {

    @Value("#{target.bookToday}")
    Long getBookToday();

}
