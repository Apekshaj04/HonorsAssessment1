package com.example.demo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest
class TicketManagementApplicationTests {

    static PostgreSQLContainer<?> postgresContainer;

    @BeforeAll
    static void setUp() {
        // Try to start testcontainers if Docker available; otherwise fallback to your existing DB details
        try {
            postgresContainer = new PostgreSQLContainer<>("postgres:15")
                    .withDatabaseName("flight_management_eldd")
                    .withUsername("flight_management_eldd_user")
                    .withPassword("password");
            postgresContainer.start();

            System.setProperty("spring.datasource.url", postgresContainer.getJdbcUrl());
            System.setProperty("spring.datasource.username", postgresContainer.getUsername());
            System.setProperty("spring.datasource.password", postgresContainer.getPassword());

        } catch (Exception e) {
            // If Testcontainers can't run (e.g. no Docker), fallback to your DB config
            System.setProperty("spring.datasource.url", "jdbc:postgresql://dpg-d0jk0n63jp1c73a17i00-a.oregon-postgres.render.com:5432/flight_management_eldd");
            System.setProperty("spring.datasource.username", "flight_management_eldd_user");
            System.setProperty("spring.datasource.password", "De69GXFPcWQZcD8zsp4VCupMdgQEYnVj");
        }
    }

    @Test
    void contextLoads() {
    }
}
