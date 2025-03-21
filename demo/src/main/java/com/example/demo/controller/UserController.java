package com.example.demo.controller;


import com.example.demo.dto.*;
import com.example.demo.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    public UserServices userServices;

    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return userServices.createUser(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id,@RequestBody User user) {
        return userServices.updateUser(user,id);
    }
    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        return  userServices.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id) {
        return userServices.deleteUser(id);
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody UserEntry user) {
            return userServices.loginUser(user);
    }

    @GetMapping("/{id}/getFlights")
    public ResponseEntity<List<Flight>> getFlights(@PathVariable int id) {
        return userServices.getFlights(id);
    }

    @PostMapping("/bookTicket/{flightId}")
    public ResponseEntity<Map<String, Object>> bookTicket(@PathVariable long flightId, @RequestBody TicketRequest request) {
        return userServices.bookTicket(flightId, request.getUserId());
    }



}
