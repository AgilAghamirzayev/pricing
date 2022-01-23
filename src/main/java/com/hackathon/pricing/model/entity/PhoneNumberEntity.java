package com.hackathon.pricing.model.entity;

import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "phone_number_table")
public class PhoneNumberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phone_id")
    private Long id;

    @Column(name="customer_id")
    private Long customerId;

    @Column(name = "number")
    private String number;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "is_sold")
    private Boolean isSold;

    @Column(name = "is_broned")
    private Boolean isBroned;

    @Column(name="branch_id")
    private Long branchId;
}
