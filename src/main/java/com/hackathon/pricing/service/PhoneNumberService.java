package com.hackathon.pricing.service;

import com.hackathon.pricing.model.entity.CustomerEntity;
import com.hackathon.pricing.model.entity.PhoneNumberEntity;
import com.hackathon.pricing.model.request.PhoneNumberRequest;
import com.hackathon.pricing.model.response.PhoneNumberResponse;

import java.util.List;

public interface PhoneNumberService {
    PhoneNumberEntity updatePhoneNumber(CustomerEntity customerEntity, Long phoneNumberId);

    List<PhoneNumberResponse> findAllByPattern(String pattern);

    PhoneNumberEntity getPhoneById(Long id);

    void savePhoneNumbers(List<PhoneNumberRequest> requestList);

    void savePhoneNumber(PhoneNumberEntity phone);
}
