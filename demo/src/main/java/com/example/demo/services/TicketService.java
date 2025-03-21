package com.example.demo.services;

import com.example.demo.dto.Ticket;
import com.example.demo.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class TicketService {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private TicketRepository ticketRepository;

    public ResponseEntity<Ticket> getTicketDetails(Long ticketId) {
        Optional<Ticket> ticket = ticketRepository.findById(ticketId);

        if (ticket.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ticket.get());
    }

    public ResponseEntity<Ticket> cancelTicket(Long ticketId) {
        Optional<Ticket> ticket = ticketRepository.findById(ticketId);
        if (ticket.isEmpty()) {
            return ResponseEntity.notFound().build();

        }
        Ticket ticket1 = ticket.get();
        Long flightId = ticket1.getFlight().getId();
        ticketRepository.deleteById(ticketId);
        String flightServiceUrl = "http://localhost:9090/user-mgmt/flights/updateSeats/" + flightId;
        restTemplate.put(flightServiceUrl, null);

        return ResponseEntity.ok(ticket1);
    }


}

