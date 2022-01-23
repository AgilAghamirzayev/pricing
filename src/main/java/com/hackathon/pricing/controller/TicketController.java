package com.hackathon.pricing.controller;

import com.hackathon.pricing.model.shared.RestResponse;
import com.hackathon.pricing.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/ticket")
public class TicketController {

    private final TicketService ticketService;

    @PutMapping("/{ticket-id}/complete")
    ResponseEntity<RestResponse<Object>> completeTicket(@PathVariable(name = "ticket-id") Long ticketId) {
        ticketService.completeTicket(ticketId);
        return ResponseEntity.ok(RestResponse.of(null));
    }

    @PutMapping("/{ticket-id}/expire")
    ResponseEntity<RestResponse<Object>> expireTicket(@PathVariable(name = "ticket-id") Long ticketId) {
        ticketService.expireTicket(ticketId);
        return ResponseEntity.ok(RestResponse.of(null));
    }

}
