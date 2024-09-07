package com.rohitbaranwal.customerservice.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "address")
@ToString(exclude = "customer")
@JsonIgnoreProperties({"customer"})
public class Addresses implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id")
    UUID addressId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    Customer customer;

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    AddressType type;

    @Column(name = "address_1")
    String address1;

    @Column(name = "address_2")
    String address2;

    @Column(name = "city")
    String city;

    @Column(name = "state")
    String state;

    @Column(name = "zipcode")
    String zipcode;

}
