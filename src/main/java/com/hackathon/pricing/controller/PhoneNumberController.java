package com.hackathon.pricing.controller;

import com.hackathon.pricing.model.request.PhoneNumberRequest;
import com.hackathon.pricing.model.response.PhoneNumberResponse;
import com.hackathon.pricing.model.shared.RestResponse;
import com.hackathon.pricing.service.PhoneNumberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Api(tags = "Phone number API")
@Validated
public class PhoneNumberController {

    private final PhoneNumberService phoneNumberService;

    @GetMapping("customer/phone-list/{pattern}")
    @ApiOperation(value = "Get phone numbers", notes = "Retrieves phone numbers by pattern")
    public ResponseEntity<RestResponse<List<PhoneNumberResponse>>> getPhoneNumbersByPattern(
            @PathVariable(name = "pattern") String pattern) {
        return ResponseEntity.ok(RestResponse.of(phoneNumberService.findAllByPattern(pattern)));
    }

    @PostMapping("admin/phone-list/create")
    @ApiOperation(value = "Add phone numbers", notes = "Add phone numbers")
    public ResponseEntity<RestResponse<Object>> addPhoneNumbers(
            @RequestBody @Valid List<PhoneNumberRequest> requestList) {
        phoneNumberService.savePhoneNumbers(requestList);
        return ResponseEntity.status(HttpStatus.CREATED).body(RestResponse.of(null));
    }
}
