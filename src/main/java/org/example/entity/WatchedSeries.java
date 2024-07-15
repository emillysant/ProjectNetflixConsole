package org.example.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * This class represents a record of a watched series episode in the application.
 * It is mapped to the "watched_series" table in the database.
 */
@Entity
@Table(name = "watched_series")
public class WatchedSeries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne()
    @JoinColumn(name = "profile", nullable = false)
    private Profile profile;

    @ManyToOne()
    @JoinColumn(name = "episode", nullable = false)
    private SeriesEpisode seriesEpisode;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /**
     * Returns the unique identifier of the watched series episode.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the profile that watched the series episode.
     *
     * @return the profile
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * Sets the profile that watched the series episode.
     *
     * @param profile the profile to set
     */
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    /**
     * Returns the series episode that was watched.
     *
     * @return the series episode
     */
    public SeriesEpisode getSeriesEpisode() {
        return seriesEpisode;
    }

    /**
     * Sets the series episode that was watched.
     *
     * @param seriesEpisode the series episode to set
     */
    public void setSeriesEpisode(SeriesEpisode seriesEpisode) {
        this.seriesEpisode = seriesEpisode;
    }

    /**
     * Returns the date and time when the series episode was watched.
     *
     * @return the created at
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
