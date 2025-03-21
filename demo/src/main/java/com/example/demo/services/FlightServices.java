package com.example.demo.services;
import com.example.demo.dto.Flight;
import com.example.demo.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class FlightServices {
    @Autowired
    FlightRepository flightRepository;

//    Fetching flights by Id
    public ResponseEntity<Flight> getFlightById(long id){
        Optional<Flight> flight = flightRepository.findById(id);
        if(flight.isPresent()){
            return ResponseEntity.ok(flight.get());
        }
        return ResponseEntity.notFound().build();
    }

//    Creating Flight
    public ResponseEntity<Flight> createFlight(Flight flight) {
        if (flight.getId() != null && flightRepository.existsById(flight.getId())) {
            return ResponseEntity.badRequest().build();
        }

        Flight savedFlight = flightRepository.save(flight);
        return ResponseEntity.ok(savedFlight);
    }

//deleting flight
    public ResponseEntity<Flight> deleteFlight(Flight flight) {
        Optional<Flight> savedFlight = flightRepository.findById(flight.getId());
        if (savedFlight.isPresent()) {
            flightRepository.delete(flight);
            return ResponseEntity.ok(flight);

        }
        return ResponseEntity.notFound().build();
    }

//    Updating flight by Id
    public ResponseEntity<Flight> updateFlight(long id, Flight flight) {
        Optional<Flight> savedFlight = flightRepository.findById(id);

        if (savedFlight.isPresent()) {
            Flight existingFlight = savedFlight.get();

            // Update only non-null fields
            if (flight.getPrice() != 0) {
                existingFlight.setPrice(flight.getPrice());
            }
            if (flight.getFlightName() != null) {
                existingFlight.setFlightName(flight.getFlightName());
            }
            if (flight.getTotalSeats() != 0) {
                existingFlight.setTotalSeats(flight.getTotalSeats());
            }
            if (flight.getAirline() != null) {
                existingFlight.setAirline(flight.getAirline());
            }
            if (flight.getDepartureTime() != null) {
                existingFlight.setDepartureTime(flight.getDepartureTime());
            }
            if (flight.getArrivalTime() != null) {
                existingFlight.setArrivalTime(flight.getArrivalTime());
            }

            flightRepository.save(existingFlight);
            return ResponseEntity.ok(existingFlight);
        }

        return ResponseEntity.notFound().build();
    }

//getting all flights
    public ResponseEntity<List<Flight>> getAllFlights(String name) {
        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<Flight> savedFlights = flightRepository.findAllByFlightName(name);
        if (savedFlights.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }

        return ResponseEntity.ok(savedFlights);
    }


//    increasing seat
    public ResponseEntity<String> increaseSeats(Long flightId) {
        Optional<Flight> flightOptional = flightRepository.findById(flightId);

        if (flightOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Flight not found");
        }

        Flight flight = flightOptional.get();
        flight.setTotalSeats(flight.getTotalSeats() + 1);
        flightRepository.save(flight);

        return ResponseEntity.ok("Seats updated successfully");
    }


}
