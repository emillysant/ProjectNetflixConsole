package org.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "watched_series")
public class WatchedSeries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
