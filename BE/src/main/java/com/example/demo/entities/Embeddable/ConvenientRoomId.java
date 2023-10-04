package com.example.demo.entities.Embeddable;

import com.example.demo.entities.Convenient;
import com.example.demo.entities.Room;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class ConvenientRoomId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "room")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "convenient")
    private Convenient convenient;
}
