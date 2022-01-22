package com.hackathon.pricing.service;

import com.hackathon.pricing.model.entity.CustomerEntity;
import com.hackathon.pricing.model.entity.PhoneNumberEntity;
import com.hackathon.pricing.model.request.TicketRequest;

public interface TicketService {

    void saveTicket(CustomerEntity customerEntity, PhoneNumberEntity phoneNumberEntity);

    void updateTicket(Long ticketId, TicketRequest ticketRequest);
}
