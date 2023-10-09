package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "gender")
    private Boolean gender;

    @Column(name = "address")
    private String address;

    @Column(name = "phonepumber")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "identificationnumber")
    private String identificationNumber;

    @Column(name = "avatarurl")
    private String avatarUrl;

    @Column(name = "point")
    private Integer point;

    @Column(name = "status")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "rank")
    private CustomerRank rank;

    @ManyToOne
    @JoinColumn(name = "[role]")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "presenter")
    private User presenter;

    @ManyToOne
    @JoinColumn(name = "createdperson")
    private User createdperson;

    @ManyToOne
    @JoinColumn(name = "updatedperson")
    private User updatedperson;


    @Column(name = "createddate")
    private Date createdDate;

    @Column(name = "updatedDate")
    private Date updatedDate;
}
