package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore  // Prevents returning full User object in JSON response
    private User user;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    private LocalDateTime bookingDate;

    public Ticket() {}

    public Ticket(User user, Flight flight, LocalDateTime bookingDate) {
        this.user = user;
        this.flight = flight;
        this.bookingDate = bookingDate;
    }

    public Ticket(User user, Flight flight) {
        this.user = user;
        this.flight = flight;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Flight getFlight() { return flight; }
    public void setFlight(Flight flight) { this.flight = flight; }

    public LocalDateTime getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDateTime bookingDate) { this.bookingDate = bookingDate; }
}
