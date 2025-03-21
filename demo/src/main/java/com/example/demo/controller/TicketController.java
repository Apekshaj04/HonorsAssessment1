package com.example.demo.controller;

import com.example.demo.dto.Ticket;
import com.example.demo.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/{ticketId}")
    public ResponseEntity<Ticket> getTicketDetails(@PathVariable Long ticketId) {
        return ticketService.getTicketDetails(ticketId);
    }
    @DeleteMapping("/cancel/{ticketId}")
    public ResponseEntity<Ticket> cancelTicket(@PathVariable Long ticketId) {
        return ticketService.cancelTicket(ticketId);
    }
}
