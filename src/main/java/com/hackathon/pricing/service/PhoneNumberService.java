package com.hackathon.pricing.service;

import com.hackathon.pricing.model.entity.CustomerEntity;
import com.hackathon.pricing.model.entity.PhoneNumberEntity;
import com.hackathon.pricing.model.response.PhoneNumberResponse;

import java.util.List;

public interface PhoneNumberService {
    void buyPhoneNumber(CustomerEntity customerEntity, Long phoneNumberId);

    List<PhoneNumberResponse> findAllByPattern(String pattern);

    PhoneNumberEntity getPhoneById(Long id);
}
