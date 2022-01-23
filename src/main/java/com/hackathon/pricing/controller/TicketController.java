package com.hackathon.pricing.controller;

import com.hackathon.pricing.model.shared.RestResponse;
import com.hackathon.pricing.service.TicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/ticket")
@Api(tags = "Ticket API")
@Validated
public class TicketController {

    private final TicketService ticketService;

    @PutMapping("/{ticket-id}/complete")
    @ApiOperation(value = "Complete ticket", notes = "Complete ticket by id")
    ResponseEntity<RestResponse<Object>> completeTicket(@PathVariable(name = "ticket-id") Long ticketId) {
        ticketService.completeTicket(ticketId);
        return ResponseEntity.ok(RestResponse.of(null));
    }

    @PutMapping("/{ticket-id}/expiry")
    @ApiOperation(value = "Expiry ticket", notes = "Expiry ticket by id")
    ResponseEntity<RestResponse<Object>> expiryTicket(@PathVariable(name = "ticket-id") Long ticketId) {
        ticketService.expiryTicket(ticketId);
        return ResponseEntity.ok(RestResponse.of(null));
    }

}
