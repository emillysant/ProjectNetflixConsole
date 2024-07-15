package org.example.entity;

import jakarta.persistence.*;

/**
 * Represents an episode of a series.
 *
 * @author Douglas Lima
 * @since 1.0
 */
@Entity
@Table(name = "series_episodes")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int season;
    @Column(name = "order_number")
    private int orderNumber;
    private String title;

    /**
     * Returns the unique identifier of the episode.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the episode.
     *
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the season number of the episode.
     *
     * @return the season
     */
    public int getSeason() {
        return season;
    }

    /**
     * Sets the season number of the episode.
     *
     * @param season the season to set
     */
    public void setSeason(int season) {
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
     * @param order_number the order number to set
     */
    public void setOrderNumber(int order_number) {
        this.orderNumber = order_number;
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
}
