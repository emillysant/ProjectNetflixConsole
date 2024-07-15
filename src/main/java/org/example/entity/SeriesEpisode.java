package org.example.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Represents an episode of a series.
 *
 * This class is an entity for the database table "series_episodes". It contains fields for the episode's ID,
 * the season it belongs to, the order number within the season, the title, and a set of watched series records.
 *
 * The class implements the Comparable interface to compare episodes based on their order numbers.
 */
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
    private Set<WatchedSeries> watchedSeries = new HashSet<>();

    /**
     * Returns the ID of the episode.
     *
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the episode.
     *
     * @param id the ID to set
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Returns the season the episode belongs to.
     *
     * @return the season
     */
    public SeriesSeason getSeason() {
        return season;
    }

    /**
     * Sets the season the episode belongs to.
     *
     * @param season the season to set
     */
    public void setSeason(SeriesSeason season) {
        this.season = season;
    }

    /**
     * Returns the order number of the episode within its season.
     *
     * @return the order number
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * Sets the order number of the episode within its season.
     *
     * @param orderNumber the order number to set
     */
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * Returns the title of the episode.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the episode.
     *
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns a set of watched series records associated with the episode.
     *
     * @return the set of watched series records
     */
    public Set<WatchedSeries> getWatchedSeries() {
        return watchedSeries;
    }

    /**
     * Compares this episode with the specified episode for order.
     *
     * @param o the episode to be compared
     * @return a negative integer, zero, or a positive integer as this episode's order number
     *         is less than, equal to, or greater than the specified episode's order number
     */
    @Override
    public int compareTo(SeriesEpisode o) {
        return Integer.compare(getOrderNumber(), o.getOrderNumber());
    }
}
