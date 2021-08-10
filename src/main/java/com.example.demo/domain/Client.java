package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "client")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    // !!!!! OSOBNA TABELA NA ZAMÓWIENIE!!!!!!
    //private LocalDateTime placedAt;

    @Column(name = "name")
    private String name;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip")
    private String zip;

    @Column(name = "cc_number")
    private String ccNumber;

    @Column(name = "cc_expiration")
    private String ccExpiration;

    @Column(name = "cc_cvv")
    private String ccCVV;

    @Column(name = "email")
    private String email;


}
