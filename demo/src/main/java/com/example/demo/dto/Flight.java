package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String airline;

    @Column(nullable = false)
    private String flightName;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int totalSeats;

    @Column(nullable = false, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime arrivalTime;

    @Column(nullable = false, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime departureTime;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    // Constructors
    public Flight() {}

    public Flight(String airline, String flightName, double price, int totalSeats,
                  LocalDateTime arrivalTime, LocalDateTime departureTime, User user) {
        this.airline = airline;
        this.flightName = flightName;
        this.price = price;
        this.totalSeats = totalSeats;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.user = user;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Object getDeparture() {
        return  departureTime;
    }


    public Object getDestination() {
        return "Nagpur";
    }

    public Object getFlightNumber() {
        return id;
    }

    public Object getArrival() {
        return arrivalTime;
    }
}
