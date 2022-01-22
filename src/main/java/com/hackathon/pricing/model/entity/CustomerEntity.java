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
    @Column(name = "customer_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "pin")
    private String pin;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy="customer")
    private Set<PhoneNumberEntity> phoneNumbers;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "ticket_table",
            joinColumns = { @JoinColumn(name = "customer_id") },
            inverseJoinColumns = { @JoinColumn(name = "phone_id") }
    )
    Set<PhoneNumberEntity> phoneTickets = new HashSet<>();
}
