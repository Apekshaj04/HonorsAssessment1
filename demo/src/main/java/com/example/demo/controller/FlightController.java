package com.example.demo.controller;

import com.example.demo.dto.Flight;
import com.example.demo.services.FlightServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    private FlightServices flightServices;

    // ✅ Create a new flight
    @PostMapping
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        return flightServices.createFlight(flight);
    }

    // ✅ Delete a flight by ID
    @DeleteMapping
    public ResponseEntity<Flight> deleteFlight(@RequestBody Flight flight) {
        return flightServices.deleteFlight(flight);
    }

    // ✅ Update a flight
    @PutMapping("/{flightId}")
    public ResponseEntity<Flight> updateFlight(@PathVariable long flightId,@RequestBody Flight flight) {
        return flightServices.updateFlight(flightId,flight);
    }

    // ✅ Get flights (Query Parameter instead of Path Variable)
    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights(@RequestParam(required = false) String name) {
        return flightServices.getAllFlights(name);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        Flight flight = flightServices.getFlightById(id).getBody();
        if (flight == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(flight);
    }
    
    @PutMapping("/updateSeats/{flightId}")
    public ResponseEntity<String> updateFlightSeats(@PathVariable Long flightId) {
        return flightServices.increaseSeats(flightId);
    }
}
