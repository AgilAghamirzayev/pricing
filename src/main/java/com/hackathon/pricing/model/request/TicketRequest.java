package com.hackathon.pricing.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequest {

    private boolean isExpired;
    private boolean isCompleted;
}
