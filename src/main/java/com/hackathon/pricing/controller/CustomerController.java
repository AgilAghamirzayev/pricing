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
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/brone")
    public ResponseEntity<RestResponse<Object>> bronePhoneNumber(@RequestBody CustomerRequest customerRequest) {
        customerService.bronePhoneNumber(customerRequest);
        return ResponseEntity.ok(RestResponse.of(null));
    }
}
