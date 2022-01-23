package com.hackathon.pricing.controller;

import com.hackathon.pricing.model.request.PhoneNumberRequest;
import com.hackathon.pricing.model.response.PhoneNumberResponse;
import com.hackathon.pricing.model.shared.RestResponse;
import com.hackathon.pricing.service.PhoneNumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PhoneNumberController {

    private final PhoneNumberService phoneNumberService;

    @GetMapping("customer/phone-list/{pattern}")
    public ResponseEntity<RestResponse<List<PhoneNumberResponse>>> getPhoneNumbersByPattern(
            @PathVariable(name = "pattern") String pattern) {
        return ResponseEntity.ok(RestResponse.of(phoneNumberService.findAllByPattern(pattern)));
    }

    @PostMapping("admin/phone-list/create")
    public ResponseEntity<RestResponse<Object>> addPhoneNumbers(@RequestBody List<PhoneNumberRequest> requestList) {
        phoneNumberService.savePhoneNumbers(requestList);
        return ResponseEntity.status(HttpStatus.CREATED).body(RestResponse.of(null));
    }
}
