package com.hackathon.pricing.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "phone_number_table")
public class PhoneNumberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phone_id")
    private Long id;

    @Column(name = "number")
    private String number;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "is_sold")
    private Boolean isSold;

    @Column(name = "is_broned")
    private Boolean isBroned;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="customer_id")
    private CustomerEntity customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="branch_id")
    private BranchEntity branch;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "phone")
    @Builder.Default
    private Set<TicketEntity> tickets = new HashSet<>();
}
