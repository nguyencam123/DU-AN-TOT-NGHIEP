package com.example.demo.entities;


import com.example.demo.entities.base.PrimaryEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "payment")
@Getter
@Setter
public class Payment extends PrimaryEntity {

    private String name;

    @Column(name = "[desc]")
    private String desc;

}
