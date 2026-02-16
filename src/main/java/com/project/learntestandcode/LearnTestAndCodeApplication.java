package com.project.learntestandcode;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootApplication
public class LearnTestAndCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnTestAndCodeApplication.class, args);
    }

    @Bean
    CommandLineRunner verifyConnection(JdbcTemplate jdbcTemplate) {
        return args -> {
            System.out.println("\n--- DATABASE CONNECTION CHECK ---");
            try {
                // This executes a simple 'ping' query that doesn't create or modify tables
                Integer result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);

                if (result != null && result == 1) {
                    DataSource ds = jdbcTemplate.getDataSource();
                    try (Connection conn = ds.getConnection()) {
                        System.out.println(" SUCCESS: Connected to " + conn.getMetaData().getDatabaseProductName());
                        System.out.println("URL: " + conn.getMetaData().getURL());
                    }
                }
            } catch (Exception e) {
                System.err.println("FAILURE: Could not connect to the database.");
                System.err.println("REASON: " + e.getMessage());
            }
            System.out.println("----------------------------------\n");
        };
    }
}