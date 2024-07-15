package org.example.entity;

import jakarta.persistence.*;

import java.util.*;


//public class Series {


@Entity
@Table(name = "series_seasons")
public class SeriesSeason implements Comparable<SeriesSeason> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "series")
    private Series series;

    @Column(name = "order_number")
    private int orderNumber;

    @OneToMany(mappedBy = "season", fetch = FetchType.EAGER)
    private SortedSet<SeriesEpisode> episodes = new TreeSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Set<SeriesEpisode> getEpisodes() {
        return episodes;
    }

    @Override
    public int compareTo(SeriesSeason o) {
        return Integer.compare(getOrderNumber(), o.getOrderNumber());
    }
}
