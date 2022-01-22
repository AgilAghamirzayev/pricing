package com.hackathon.pricing.controller;

import com.hackathon.pricing.model.response.PhoneNumberResponse;
import com.hackathon.pricing.model.shared.RestResponse;
import com.hackathon.pricing.service.PhoneNumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/phone")
public class PhoneNumberController {

    private final PhoneNumberService phoneNumberService;

    @GetMapping("/list/{pattern}")
    public ResponseEntity<RestResponse<List<PhoneNumberResponse>>> getPhoneNumbersByPattern(@PathVariable(name = "pattern") String pattern) {
        return ResponseEntity.ok(RestResponse.of(phoneNumberService.findAllByPattern(pattern)));
    }
}
