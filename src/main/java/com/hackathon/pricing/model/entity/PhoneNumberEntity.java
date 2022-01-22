package com.hackathon.pricing.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "phone_number_table")
public class PhoneNumberEntity {

    @Id
    @Column(name = "phone_id")
    private Long id;

    @Column(name = "number")
    private String number;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "isSold")
    private Boolean isSold;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name="branch_id")
    private BranchEntity branch;

    @ManyToMany(mappedBy = "phoneTickets")
    private Set<CustomerEntity> customerTickets = new HashSet<>();


}
