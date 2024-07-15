package org.example.entity;

import jakarta.persistence.*;

import java.util.*;

/**
 * Represents a season of a series in a database.
 * This class is annotated with JPA annotations to map it to a database table.
 * It implements the Comparable interface to compare seasons based on their order number.
 */
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

    /**
     * Returns the unique identifier of the season.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the season.
     *
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the series to which this season belongs.
     *
     * @return the series
     */
    public Series getSeries() {
        return series;
    }

    /**
     * Sets the series to which this season belongs.
     *
     * @param series the series to set
     */
    public void setSeries(Series series) {
        this.series = series;
    }

    /**
     * Returns the order number of the season within the series.
     *
     * @return the orderNumber
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * Sets the order number of the season within the series.
     *
     * @param orderNumber the orderNumber to set
     */
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * Returns a set of episodes belonging to this season.
     *
     * @return the episodes
     */
    public Set<SeriesEpisode> getEpisodes() {
        return episodes;
    }

    /**
     * Compares this season with another season based on their order number.
     *
     * @param o the season to compare with
     * @return a negative integer, zero, or a positive integer as this season's order number
     *         is less than, equal to, or greater than the specified season's order number.
     */
    @Override
    public int compareTo(SeriesSeason o) {
        return Integer.compare(getOrderNumber(), o.getOrderNumber());
    }
}
