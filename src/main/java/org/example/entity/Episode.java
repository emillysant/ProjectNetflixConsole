package org.example.entity;

import jakarta.persistence.*;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int order_number) {
        this.orderNumber = order_number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
