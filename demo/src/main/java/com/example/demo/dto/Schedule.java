package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@Table(name = "schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "schedule_day", nullable = false) // Renamed from "day"
    private String scheduleDay;

    @Column(name = "departure_time", nullable = false)
    private String departureTime;

    @Column(name = "arrival_time", nullable = false)
    private String arrivalTime;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    @JsonIgnore // Prevents infinite recursion in JSON responses
    private Flight flight;
}
