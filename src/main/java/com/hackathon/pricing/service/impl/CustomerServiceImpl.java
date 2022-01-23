package com.hackathon.pricing.service.impl;

import com.hackathon.pricing.mapper.CustomerMapper;
import com.hackathon.pricing.model.entity.CustomerEntity;
import com.hackathon.pricing.model.entity.PhoneNumberEntity;
import com.hackathon.pricing.model.request.CustomerRequest;
import com.hackathon.pricing.repo.CustomerRepo;
import com.hackathon.pricing.service.CustomerService;
import com.hackathon.pricing.service.PhoneNumberService;
import com.hackathon.pricing.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;
    private final PhoneNumberService phoneNumberService;
    private final TicketService ticketService;
    private final CustomerMapper customerMapper;

    @Override
    public void bronePhoneNumber(CustomerRequest customerRequest) {
        CustomerEntity customerEntity = customerRepo.findByPin(customerRequest.getPin())
                .orElseGet(() -> customerRepo.save(customerMapper.entityToDto(customerRequest)));
        PhoneNumberEntity phoneNumberEntity = phoneNumberService.updatePhoneNumber(customerEntity,
                customerRequest.getPhoneNumberId());
        ticketService.saveTicket(customerEntity.getId(), phoneNumberEntity.getId());
    }
}
