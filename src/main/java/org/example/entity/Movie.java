package org.example.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "films")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    @Column(name = "release_date")
    private LocalDate releaseDate;
    private String description;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "film_categories",
            joinColumns = {@JoinColumn(name = "film")},
            inverseJoinColumns = {@JoinColumn(name = "category")}
    )
    private Set<Category> categories = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public int getReleaseYear() {
        return releaseDate.getYear();
    }
}
