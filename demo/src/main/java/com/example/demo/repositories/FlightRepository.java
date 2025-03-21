package com.example.demo.repositories;

import com.example.demo.dto.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    Optional<Flight> findById(long id);
    List<Flight> findAllByFlightName(String flightName);
}
