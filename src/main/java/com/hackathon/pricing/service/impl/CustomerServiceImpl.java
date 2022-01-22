package com.hackathon.pricing.service.impl;

import com.hackathon.pricing.model.entity.CustomerEntity;
import com.hackathon.pricing.mapper.CustomerMapper;
import com.hackathon.pricing.model.entity.PhoneNumberEntity;
import com.hackathon.pricing.model.entity.TicketEntity;
import com.hackathon.pricing.model.request.CustomerRequest;
import com.hackathon.pricing.repo.CustomerRepo;
import com.hackathon.pricing.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;
    private final PhoneNumberServiceImpl phoneNumberService;
    private final CustomerMapper customerMapper;

    @Override
    public void buyPhoneNumber(CustomerRequest customerRequest) {
        CustomerEntity customerEntity = customerRepo.findByPin(customerRequest.getPin()).orElseGet(() ->
            customerMapper.entityToDto(customerRequest)
        );
        PhoneNumberEntity phone = phoneNumberService.getPhoneById(customerRequest.getPhoneNumberId());
        phone.setIsBroned(true);
        customerEntity.getPhoneNumbers().add(phone);
        customerRepo.save(customerEntity);
        TicketEntity ticketEntity = TicketEntity.builder().customer(customerEntity).phone(phone).build();
    }
}
