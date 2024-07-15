package org.example.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents a movie entity in a movie database.
 * It is annotated with JPA annotations to map it to a database table named "films".
 */
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

    /**
     * A many-to-many relationship with Category entity.
     * The relationship is mapped through the "film_categories" join table.
     */
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "film_categories",
            joinColumns = {@JoinColumn(name = "film")},
            inverseJoinColumns = {@JoinColumn(name = "category")}
    )
    private Set<Category> categories = new HashSet<>();

    /**
     * A one-to-many relationship with WatchedMovie entity.
     * The relationship is mapped by the "movie" field in the WatchedMovie entity.
     */
    @OneToMany(mappedBy = "movie")
    private Set<WatchedMovie> watchedMovies;

    /**
     * Returns the unique identifier of the movie.
     *
     * @return the unique identifier of the movie
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the title of the movie.
     *
     * @return the title of the movie
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the movie.
     *
     * @param title the new title of the movie
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the release date of the movie.
     *
     * @return the release date of the movie
     */
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    /**
     * Sets the release date of the movie.
     *
     * @param releaseDate the new release date of the movie
     */
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * Returns the description of the movie.
     *
     * @return the description of the movie
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the movie.
     *
     * @param description the new description of the movie
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the set of categories associated with the movie.
     *
     * @return the set of categories associated with the movie
     */
    public Set<Category> getCategories() {
        return categories;
    }

    /**
     * Returns the set of watched movies associated with the movie.
     *
     * @return the set of watched movies associated with the movie
     */
    public Set<WatchedMovie> getWatchedMovies() {
        return watchedMovies;
    }

    /**
     * Returns the release year of the movie.
     *
     * @return the release year of the movie
     */
    public int getReleaseYear() {
        return releaseDate.getYear();
    }
}
