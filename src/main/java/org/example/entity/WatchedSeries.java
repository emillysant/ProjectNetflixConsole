package org.example.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

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

    public int getId() {
        return id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public SeriesEpisode getSeriesEpisode() {
        return seriesEpisode;
    }

    public void setSeriesEpisode(SeriesEpisode seriesEpisode) {
        this.seriesEpisode = seriesEpisode;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
