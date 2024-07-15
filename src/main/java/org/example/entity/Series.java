package org.example.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Represents a TV series entity.
 *
 * @author Igor Peronico
 */
@Entity
@Table(name = "series")
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    @Column(name = "release_date")
    private LocalDate releaseDate;
    private String description;

    /**
     * Represents the seasons of a TV series.
     *
     * This field is annotated with {@link OneToMany} to establish a one-to-many relationship with the {@link SeriesSeason} entity.
     * The relationship is mapped by the "series" field in the {@link SeriesSeason} entity.
     * The seasons are fetched eagerly to avoid lazy initialization exceptions.
     * The seasons are sorted by the "orderNumber" field using {@link OrderBy}.
     *
     * @return the seasons of the series
     */
    @OneToMany(mappedBy = "series", fetch = FetchType.EAGER)
    @OrderBy("orderNumber")
    private Set<SeriesSeason> seasons = new TreeSet<>();

    /**
     * Represents the categories of a TV series.
     *
     * This field is annotated with {@link ManyToMany} to establish a many-to-many relationship with the {@link Category} entity.
     * The relationship is fetched eagerly to avoid lazy initialization exceptions.
     * The cascading type is set to {@link CascadeType.ALL} to ensure that any changes made to the categories are reflected in the database.
     * The join table is named "series_categories" and is defined using {@link JoinTable}.
     * The join columns are defined using {@link JoinColumn} and map to the "series" field in the current entity.
     * The inverse join columns are defined using {@link JoinColumn} and map to the "category" field in the {@link Category} entity.
     *
     * @return the categories of the series
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
    @JoinTable(
            name = "series_categories",
            joinColumns = {@JoinColumn(name = "series")},
            inverseJoinColumns = {@JoinColumn(name = "category")}
    )
    private SortedSet<Category> categories = new TreeSet<>();

    /**
     * Gets the unique identifier of the series.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the series.
     *
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the title of the series.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the series.
     *
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the release date of the series.
     *
     * @return the releaseDate
     */
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    /**
     * Sets the release date of the series.
     *
     * @param releaseDate the releaseDate to set
     */
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * Gets the description of the series.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the series.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the seasons of the series.
     *
     * @return the seasons
     */
    public Set<SeriesSeason> getSeasons() {
        return seasons;
    }

    /**
     * Gets the categories of the series.
     *
     * @return the categories
     */
    public Set<Category> getCategories() {
        return categories;
    }

    /**
     * Gets the release year of the series.
     *
     * @return the release year
     */
    public int getReleaseYear() {
        return releaseDate.getYear();
    }
}
