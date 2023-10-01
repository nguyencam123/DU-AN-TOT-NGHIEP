package com.example.demo.models.Embeddable;

import com.example.demo.models.Booking;
import com.example.demo.models.Room;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class DetailBookingId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "booking", insertable = false,updatable = false)
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "room")
    private Room room;
}
