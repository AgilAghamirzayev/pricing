package com.hackathon.pricing.service.impl;

import com.hackathon.pricing.exception.classes.RecordNotFoundException;
import com.hackathon.pricing.model.entity.PhoneNumberEntity;
import com.hackathon.pricing.model.entity.TicketEntity;
import com.hackathon.pricing.repo.TicketRepo;
import com.hackathon.pricing.service.PhoneNumberService;
import com.hackathon.pricing.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepo ticketRepo;
    private final PhoneNumberService phoneNumberService;

    @Override
    public void saveTicket(Long customerId, Long phoneId) {
        TicketEntity ticketEntity = ticketRepo.save(TicketEntity.builder().customerId(customerId).phoneId(phoneId).build());
        log.info("ticket saved: {}", ticketEntity);
    }

    @Override
    public void completeTicket(Long ticketId) {
        ticketRepo.findById(ticketId).ifPresentOrElse(entity -> {
            entity.setCompleted(true);
            TicketEntity ticketEntity = ticketRepo.save(entity);
            log.info("ticket completed: {}", ticketEntity);
            PhoneNumberEntity phone = phoneNumberService.getPhoneById(entity.getPhoneId());
            phone.setIsBroned(false);
            phone.setIsSold(true);
            phoneNumberService.savePhoneNumber(phone);
        }, () -> {throw new RecordNotFoundException(String.format("ticket not found by id %s", ticketId));});
    }

    @Override
    public void expireTicket(Long ticketId) {
        ticketRepo.findById(ticketId).ifPresentOrElse(entity -> {
            entity.setExpired(true);
            TicketEntity ticketEntity = ticketRepo.save(entity);
            log.info("ticket expired: {}", ticketEntity);
            PhoneNumberEntity phone = phoneNumberService.getPhoneById(entity.getPhoneId());
            phone.setIsBroned(false);
            phoneNumberService.savePhoneNumber(phone);
        }, () -> {throw new RecordNotFoundException(String.format("ticket not found by id %s", ticketId));});
    }
}
