package com.project.learntestandcode.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name="books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String title;
}
