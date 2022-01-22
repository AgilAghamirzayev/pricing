package com.hackathon.pricing.service.impl;

import com.hackathon.pricing.exception.classes.RecordNotFoundException;
import com.hackathon.pricing.mapper.PhoneNumberMapper;
import com.hackathon.pricing.model.entity.CustomerEntity;
import com.hackathon.pricing.model.entity.PhoneNumberEntity;
import com.hackathon.pricing.model.response.PhoneNumberResponse;
import com.hackathon.pricing.repo.PhoneNumberRepo;
import com.hackathon.pricing.service.PhoneNumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhoneNumberServiceImpl implements PhoneNumberService {

    private final PhoneNumberRepo phoneNumberRepo;
    private final PhoneNumberMapper phoneNumberMapper;

    @Override
    public void buyPhoneNumber(CustomerEntity customerEntity, Long phoneNumberId) {
        PhoneNumberEntity phoneNumber = phoneNumberRepo.findById(phoneNumberId).orElseThrow(() -> new RecordNotFoundException("Phone number not found by id"));
        phoneNumber.setIsSold(true);
        phoneNumber.setCustomer(customerEntity);
    }

    @Override
    public List<PhoneNumberResponse> findAllByPattern(String pattern) {
        List<PhoneNumberEntity> phoneNumberEntities = phoneNumberRepo.findAllByNumberLikeAndIsSoldFalse(pattern);
        if (phoneNumberEntities.isEmpty()) {
            throw new RecordNotFoundException("Phone number not found by pattern");
        }
        return phoneNumberMapper.entityToResponse(phoneNumberEntities);
    }


}
