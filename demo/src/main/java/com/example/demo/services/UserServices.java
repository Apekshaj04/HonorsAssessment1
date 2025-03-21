package com.example.demo.services;

import com.example.demo.dto.*;
import com.example.demo.repositories.FlightRepository;
import com.example.demo.repositories.TicketRepository;
import com.example.demo.repositories.UserRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserServices {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    TicketRepository ticketRepository;

    public static final String FLIGHT_SERVICE_URL ="http://localhost:9090/mgmt/flights";
    public static final String USER_SERVICE_URL ="http://localhost:9090/mgmt/users";
    public ResponseEntity<User> createUser(User user) {
        Optional<User> savedUser = userRepository.findByEmail(user.getEmail());
        if (savedUser.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<User> updateUser(User user, int id) {
        Optional<User> savedUser = userRepository.findById(id);
        savedUser.ifPresent(value -> value.setName(user.getName()));
        savedUser.ifPresent(value -> value.setEmail(user.getEmail()));
        savedUser.ifPresent(value -> value.setPassword(user.getPassword()));
        userRepository.save(savedUser.get());
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<User> loginUser(UserEntry user) {
        String email = user.getEmail();
        Optional<User> savedUser = userRepository.findByEmail(email);
        return savedUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    public ResponseEntity<User> deleteUser(@PathVariable int id) {
        Optional<User> savedUser = userRepository.findById(id);
        savedUser.ifPresent(user -> userRepository.delete(user));
        return ResponseEntity.ok().build();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).get();
    }

    public ResponseEntity<List<Flight>> getFlights(int id) {
        Optional<User> savedUser = userRepository.findById(id);

        return savedUser.map(user -> ResponseEntity.ok(List.copyOf(user.getFlights()))).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList()));
    }

    public ResponseEntity<Map<String, Object>> bookTicket(long flightId, long userId) {
        // ✅ Fetch Flight Details
        ResponseEntity<Flight> flightResponse = restTemplate.getForEntity(
                "http://localhost:9090/user-mgmt/flights/" + flightId, Flight.class);

        if (!flightResponse.getStatusCode().is2xxSuccessful() || flightResponse.getBody() == null) {
            return ResponseEntity.badRequest().build(); // If flight is not found
        }
        Flight flight = flightResponse.getBody();

        // ✅ Fetch User Details
        ResponseEntity<User> userResponse = restTemplate.getForEntity(
                "http://localhost:9090/user-mgmt/users/" + userId, User.class);

        if (!userResponse.getStatusCode().is2xxSuccessful() || userResponse.getBody() == null) {
            return ResponseEntity.badRequest().build(); // If user is not found
        }
        User user = userResponse.getBody();

        // ✅ Create and Save Ticket
        Ticket ticket = new Ticket(user, flight);
        Ticket savedTicket = ticketRepository.save(ticket);

        // ✅ Return only necessary details
        Map<String, Object> response = new HashMap<>();
        response.put("ticketId", savedTicket.getId());
        response.put("flightId", flight.getId());
        response.put("departure", flight.getDeparture());
        response.put("destination", flight.getDestination());
        response.put("price", flight.getPrice());

        return ResponseEntity.ok(response);
    }






}
