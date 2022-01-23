package com.hackathon.pricing.controller;

import com.hackathon.pricing.model.request.CustomerRequest;
import com.hackathon.pricing.model.shared.RestResponse;
import com.hackathon.pricing.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer")
@Api(tags = "Customer panel API")
@Validated
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/brone")
    @ApiOperation(value = "Brone phone number", notes = "Brone phone number by phone number id")
    public ResponseEntity<RestResponse<Object>> bronePhoneNumber(@RequestBody @Valid CustomerRequest customerRequest) {
        customerService.bronePhoneNumber(customerRequest);
        return ResponseEntity.ok(RestResponse.of(null));
    }
}
