package com.hackathon.pricing.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "ticket_table")
public class TicketEntity {

    @Id
    @Column(name = "ticket_id")
    private Long ticketId;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "phone_id")
    private Long phoneId;
}
