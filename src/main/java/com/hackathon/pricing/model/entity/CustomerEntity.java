package com.hackathon.pricing.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer_table")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial", name = "customer_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "pin")
    private String pin;

    @Column(name = "email")
    private String email;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer", cascade = { CascadeType.ALL})
    @Builder.Default
    private Set<PhoneNumberEntity> phoneNumbers = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
    @Builder.Default
    private Set<TicketEntity> tickets = new HashSet<>();

}
