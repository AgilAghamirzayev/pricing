package com.hackathon.pricing.controller;

import com.hackathon.pricing.model.request.PhoneNumberRequest;
import com.hackathon.pricing.model.request.TicketRequest;
import com.hackathon.pricing.model.shared.RestResponse;
import com.hackathon.pricing.service.PhoneNumberService;
import com.hackathon.pricing.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final PhoneNumberService phoneNumberService;
    private final TicketService ticketService;

    @PostMapping("/add-numbers")
    public ResponseEntity<RestResponse<Object>> addPhoneNumbers(@RequestBody List<PhoneNumberRequest> requestList) {
        phoneNumberService.savePhoneNumbers(requestList);
        return ResponseEntity.status(HttpStatus.CREATED).body(RestResponse.of(null));
    }

    @PutMapping("/update-ticket/{ticket-id}")
    ResponseEntity<RestResponse<Object>> updateTicket(@PathVariable(name = "ticket-id") Long ticketId,
                                                      @RequestBody TicketRequest ticketRequest) {
        ticketService.updateTicket(ticketId, ticketRequest);
        return ResponseEntity.ok(RestResponse.of(null));
    }

}
