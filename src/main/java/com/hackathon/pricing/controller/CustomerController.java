package com.hackathon.pricing.controller;

import com.hackathon.pricing.model.request.CustomerRequest;
import com.hackathon.pricing.model.shared.RestResponse;
import com.hackathon.pricing.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/phone")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/buy")
    public ResponseEntity<RestResponse<Object>> buyPhoneNumber(@RequestBody CustomerRequest customerRequest) {
        customerService.buyPhoneNumber(customerRequest);
        return ResponseEntity.ok(RestResponse.of(null));
    }
}
