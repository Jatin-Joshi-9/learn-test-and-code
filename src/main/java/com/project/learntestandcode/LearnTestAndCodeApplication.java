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

}