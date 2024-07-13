package org.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "watched_films")
public class WatchedMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
