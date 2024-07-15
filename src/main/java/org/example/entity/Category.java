package org.example.entity;

import jakarta.persistence.*;

import java.util.Set;

/**
 * Represents a category entity in the application.
 * This class is annotated with JPA annotations to map it to a database table.
 * It implements the Comparable interface to allow sorting of categories based on their names.
 */
@Entity
@Table(name = "categories")
public class Category implements Comparable<Category> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    /**
     * A set of movies that are associated with this category.
     * This is mapped by the 'categories' field in the Movie class.
     */
    @ManyToMany(mappedBy = "categories")
    private Set<Movie> movies;

    /**
     * A set of series that are associated with this category.
     * This is mapped by the 'categories' field in the Series class.
     */
    @ManyToMany(mappedBy = "categories")
    private Set<Series> series;

    /**
     * Returns the unique identifier of the category.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name of the category.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the category.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Compares this category with the specified category for order.
     *
     * @param o the category to be compared
     * @return a negative integer, zero, or a positive integer as this category
     *         is less than, equal to, or greater than the specified category
     */
    @Override
    public int compareTo(Category o) {
        return getName().compareTo(o.getName());
    }
}
