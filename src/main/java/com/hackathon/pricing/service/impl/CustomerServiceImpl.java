package com.hackathon.pricing.service.impl;

import com.hackathon.pricing.model.entity.CustomerEntity;
import com.hackathon.pricing.mapper.CustomerMapper;
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
        CustomerEntity savedCustomer = customerRepo.findByPin(customerRequest.getPin()).orElseGet(() -> {
            CustomerEntity customerEntity = customerMapper.entityToDto(customerRequest);
            return customerRepo.save(customerEntity);
        });
        phoneNumberService.buyPhoneNumber(savedCustomer, customerRequest.getPhoneNumberId());
    }
}
