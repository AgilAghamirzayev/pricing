package com.hackathon.pricing.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "branch_table")
public class BranchEntity {

    @Id
    @Column(name = "branch_id")
    private Long id;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy="branch")
    private Set<PhoneNumberEntity> phoneNumbers;
}
