package com.example.demo.models.Embeddable;

import com.example.demo.models.Convenient;
import com.example.demo.models.Hotel;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class ConvenientHotelId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "hotel")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "convenient")
    private Convenient convenient;
}
