package com.hackathon.pricing.service.impl;

import com.hackathon.pricing.exception.classes.RecordNotFoundException;
import com.hackathon.pricing.mapper.PhoneNumberMapper;
import com.hackathon.pricing.model.entity.CustomerEntity;
import com.hackathon.pricing.model.entity.PhoneNumberEntity;
import com.hackathon.pricing.model.request.PhoneNumberRequest;
import com.hackathon.pricing.model.response.PhoneNumberResponse;
import com.hackathon.pricing.repo.PhoneNumberRepo;
import com.hackathon.pricing.service.PhoneNumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhoneNumberServiceImpl implements PhoneNumberService {

    private static final double MIN_COST = 6.0;
    private static final double NUM_LEN = 9.0;
    private static final double CONSTANT = MIN_COST / NUM_LEN;

    private final PhoneNumberRepo phoneNumberRepo;
    private final PhoneNumberMapper phoneNumberMapper;

    @Override
    public PhoneNumberEntity buyPhoneNumber(CustomerEntity customerEntity, Long phoneNumberId) {
        PhoneNumberEntity phoneNumber = phoneNumberRepo.findById(phoneNumberId).orElseThrow(() -> new RecordNotFoundException("Phone number not found by id"));
        phoneNumber.setIsBroned(true);
        phoneNumber.setCustomer(customerEntity);
        return phoneNumberRepo.save(phoneNumber);
    }

    @Override
    public List<PhoneNumberResponse> findAllByPattern(String pattern) {
        List<PhoneNumberEntity> phoneNumberEntities = phoneNumberRepo.findAllByNumberLikeAndIsSoldFalse(pattern);
        if (phoneNumberEntities.isEmpty()) {
            throw new RecordNotFoundException("Phone number not found by pattern");
        }
        return phoneNumberMapper.entityToResponse(phoneNumberEntities);
    }

    @Override
    public PhoneNumberEntity getPhoneById(Long phoneNumberId) {
        return phoneNumberRepo.findById(phoneNumberId).orElseThrow(() -> new RecordNotFoundException("Phone number not found by id"));
    }

    @Override
    public void savePhoneNumbers(List<PhoneNumberRequest> requestList) {
        phoneNumberRepo.saveAll(phoneNumberMapper.requestToEntity(requestList));
    }

    private int predictPrice(String number) {

        final Collection<Long> duplicationCounts = number.codePoints().mapToObj(Character::toString)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values();

        return specialCalculating(duplicationCounts, number.charAt(0));
    }

    private int specialCalculating(Collection<Long> values, char firstNumber) {
        int price = 0;
        for (Long position : values) {
            price += position * weight(Math.toIntExact(position));
        }

        if (values.size() == 3 || values.size() == 2) price += (MIN_COST * NUM_LEN);

        price = Math.abs(price - values.size()) * 2 + 2;

        if (firstNumber == '2') price += 60;  // for government numbers


        return price;
    }

    public double weight(int a) {

        switch (a) {
            case 1:
                return CONSTANT;
            case 2:
                return CONSTANT * 2;
            case 3:
                return CONSTANT * 3;
            case 4:
                return CONSTANT * 25;
            case 5:
                return CONSTANT * 30;
            case 6:
                return CONSTANT * 35;
            case 7:
                return CONSTANT * 40;
            case 8:
                return CONSTANT * 45;
            case 9:
                return CONSTANT * 50;
            default:
                return 0;
        }

    }

}
