package com.example.demo.models;

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
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {

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

    @Column(name = "identificationnumber")
    private String identificationNumber;

    @Column(name = "avatarurl")
    private String avatarUrl;

    @Column(name = "status")
    private Integer status;

    @Column(name = "createddate")
    private Date createdDate;

    @Column(name = "updatedDate")
    private Date updatedDate;

    @ManyToOne
    @JoinColumn(name = "createdperson")
    private  Admin adminCreated;

    @ManyToOne
    @JoinColumn(name = "updatedperson")
    private  Admin adminUpdated;
}
