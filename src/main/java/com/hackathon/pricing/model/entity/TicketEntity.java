package com.hackathon.pricing.model.entity;

import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ticket_table")
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long ticketId;

    @Column(name="customer_id")
    private Long customerId;

    @Column(name="phone_id")
    private Long phoneId;

    @Column(name = "is_expired")
    private boolean isExpired;

    @Column(name = "is_completed")
    private boolean isCompleted;

    @Column(name = "created_date")
    @Builder.Default
    private LocalDateTime createdDate = LocalDateTime.now();
}
