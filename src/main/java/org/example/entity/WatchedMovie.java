package org.example.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Represents a record of a movie watched by a user profile.
 *
 * This class is an entity in a JPA-managed database. It is associated with the "watched_films" table.
 * It contains fields for the primary key (id), the user profile (profile), the movie watched (movie),
 * and the timestamp of when the movie was watched (createdAt).
 *
 * @author Emilly
 * @version 1.0
 */
@Entity
@Table(name = "watched_films")
public class WatchedMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne()
    @JoinColumn(name = "profile", nullable = false)
    private Profile profile;

    @ManyToOne()
    @JoinColumn(name = "film", nullable = false)
    private Movie movie;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /**
     * Returns the unique identifier of the watched movie record.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the user profile associated with the watched movie.
     *
     * @return the profile
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * Sets the user profile associated with the watched movie.
     *
     * @param profile the profile to set
     */
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    /**
     * Returns the movie that was watched.
     *
     * @return the movie
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * Sets the movie that was watched.
     *
     * @param movie the movie to set
     */
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    /**
     * Returns the timestamp of when the movie was watched.
     *
     * @return the createdAt
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
