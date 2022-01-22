package com.hackathon.pricing.service.impl;

import com.hackathon.pricing.mapper.TicketMapper;
import com.hackathon.pricing.model.entity.CustomerEntity;
import com.hackathon.pricing.model.entity.PhoneNumberEntity;
import com.hackathon.pricing.model.entity.TicketEntity;
import com.hackathon.pricing.model.request.TicketRequest;
import com.hackathon.pricing.repo.TicketRepo;
import com.hackathon.pricing.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepo ticketRepo;
    private final TicketMapper ticketMapper;

    @Override
    public void saveTicket(CustomerEntity customerEntity, PhoneNumberEntity phoneNumberEntity) {
        ticketRepo.save(TicketEntity.builder().customer(customerEntity).phone(phoneNumberEntity).build());
    }

    @Override
    public void updateTicket(Long ticketId, TicketRequest ticketRequest) {
        ticketRepo.findById(ticketId).ifPresentOrElse(entity -> {
            ticketMapper.updateEntity(entity, ticketRequest);
            ticketRepo.save(entity);
        },
                () -> {throw new RuntimeException(String.format("ticket not found by id %s", ticketId));});

    }
}
