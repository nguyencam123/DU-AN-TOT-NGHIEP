package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "[role]")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Role {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "code")
    private Integer code;

    @Column(name = "name")
    private String name;

    @Column(name = "[desc]")
    private String desc;
}
