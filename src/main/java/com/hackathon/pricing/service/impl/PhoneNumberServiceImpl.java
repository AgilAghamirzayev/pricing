package com.hackathon.pricing.service.impl;

import com.hackathon.pricing.exception.classes.RecordNotFoundException;
import com.hackathon.pricing.mapper.PhoneNumberMapper;
import com.hackathon.pricing.model.entity.CustomerEntity;
import com.hackathon.pricing.model.entity.PhoneNumberEntity;
import com.hackathon.pricing.model.request.PhoneNumberRequest;
import com.hackathon.pricing.model.response.PhoneNumberResponse;
import com.hackathon.pricing.repo.PhoneNumberRepo;
import com.hackathon.pricing.service.BranchService;
import com.hackathon.pricing.service.PhoneNumberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Boolean.TRUE;

@Slf4j
@Service
@RequiredArgsConstructor
public class PhoneNumberServiceImpl implements PhoneNumberService {

    private final PhoneNumberRepo phoneNumberRepo;
    private final PhoneNumberMapper phoneNumberMapper;
    private final BranchService branchService;

    @Override
    public PhoneNumberEntity updatePhoneNumber(CustomerEntity customerEntity, Long phoneNumberId) {
        PhoneNumberEntity phoneNumber = phoneNumberRepo.findById(phoneNumberId).orElseThrow(() -> new RecordNotFoundException("Phone number not found by id"));
        phoneNumber.setIsBroned(true);
        phoneNumber.setCustomerId(customerEntity.getId());
        PhoneNumberEntity phoneNumberEntity = phoneNumberRepo.save(phoneNumber);
        log.info("phone number broned: {}", phoneNumberEntity);
        return phoneNumberEntity;
    }

    @Override
    public List<PhoneNumberResponse> findAllByPattern(String pattern) {
        List<PhoneNumberEntity> phoneNumberEntities = phoneNumberRepo.findAllByNumberLike(pattern).stream()
                .filter(num -> !TRUE.equals(num.getIsBroned()) && !TRUE.equals(num.getIsSold())).collect(Collectors.toList());
        if (phoneNumberEntities.isEmpty()) {
            throw new RecordNotFoundException("Phone number not found by pattern");
        }
        List<PhoneNumberResponse> phoneResponseList = phoneNumberMapper.entityToResponse(phoneNumberEntities);
        phoneResponseList.forEach(phone -> phone.setAddress(branchService.getAddressById(phone.getBranchId())));
        log.info("fetched phone numbers by pattern: {}", phoneResponseList);
        return phoneResponseList;
    }

    @Override
    public PhoneNumberEntity getPhoneById(Long phoneNumberId) {
        PhoneNumberEntity phoneNumberEntity = phoneNumberRepo.findById(phoneNumberId)
                .orElseThrow(() -> new RecordNotFoundException("Phone number not found by id"));
        log.info("fetched phone number by id {}", phoneNumberEntity);
        return phoneNumberEntity;
    }

    @Override
    public void savePhoneNumbers(List<PhoneNumberRequest> requestList) {
        List<PhoneNumberEntity> saved = phoneNumberRepo.saveAll(
                phoneNumberMapper.requestToEntity(requestList));
        log.info("saved phone numbers: {}", saved);
    }

    @Override
    public void savePhoneNumber(PhoneNumberEntity phoneNumberEntity) {
        PhoneNumberEntity saved = phoneNumberRepo.save(phoneNumberEntity);
        log.info("saved phone number: {}", saved);
    }


}
