package org.example.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "series_episodes")
public class SeriesEpisode implements Comparable<SeriesEpisode> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "season")
    private SeriesSeason season;

    @Column(name = "order_number")
    private int orderNumber;

    private String title;

    @OneToMany(mappedBy = "seriesEpisode")
    private Set<WatchedSeries> watchedSeries;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SeriesSeason getSeason() {
        return season;
    }

    public void setSeason(SeriesSeason season) {
        this.season = season;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<WatchedSeries> getWatchedSeries() {
        return watchedSeries;
    }

    @Override
    public int compareTo(SeriesEpisode o) {
        return Integer.compare(getOrderNumber(), o.getOrderNumber());
    }
}
