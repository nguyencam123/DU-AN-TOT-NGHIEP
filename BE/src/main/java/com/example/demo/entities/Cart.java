package com.example.demo.entities;

import com.example.demo.entities.base.PrimaryEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cart")
@Getter
@Setter
public class Cart extends PrimaryEntity {

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String homestayId;

}
