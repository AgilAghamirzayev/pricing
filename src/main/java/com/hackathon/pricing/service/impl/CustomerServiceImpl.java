package com.hackathon.pricing.service.impl;

import com.hackathon.pricing.mapper.CustomerMapper;
import com.hackathon.pricing.model.entity.CustomerEntity;
import com.hackathon.pricing.model.entity.PhoneNumberEntity;
import com.hackathon.pricing.model.request.CustomerRequest;
import com.hackathon.pricing.repo.CustomerRepo;
import com.hackathon.pricing.service.CustomerService;
import com.hackathon.pricing.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;
    private final PhoneNumberServiceImpl phoneNumberService;
    private final TicketService ticketService;
    private final CustomerMapper customerMapper;

    @Override
    public void buyPhoneNumber(CustomerRequest customerRequest) {
        CustomerEntity customerEntity = customerRepo.findByPin(customerRequest.getPin())
                .orElseGet(() -> customerRepo.save(customerMapper.entityToDto(customerRequest)));
        PhoneNumberEntity phoneNumberEntity = phoneNumberService.buyPhoneNumber(customerEntity,
                customerRequest.getPhoneNumberId());
        ticketService.saveTicket(customerEntity, phoneNumberEntity);
    }
}
