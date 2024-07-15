package org.example.entity;

import jakarta.persistence.*;

import java.util.Set;

/**
 * Represents a user profile in the application.
 * This class is annotated with JPA annotations to map it to a database table.
 */
@Entity
@Table(name = "account_profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int account;
    private String name;

    /**
     * A set of watched movies associated with this profile.
     * This is mapped to a database table using the {@link OneToMany} annotation.
     */
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private Set<WatchedMovie> watchedMovieSet;

    /**
     * Returns the unique identifier of the profile.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the profile.
     *
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the account associated with this profile.
     *
     * @return the account
     */
    public int getAccount() {
        return account;
    }

    /**
     * Sets the account associated with this profile.
     *
     * @param account the account to set
     */
    public void setAccount(int account) {
        this.account = account;
    }

    /**
     * Returns the name of the profile.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the profile.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a set of watched movies associated with this profile.
     *
     * @return the set of watched movies
     */
    public Set<WatchedMovie> getWatchedMovieSet() {
        return watchedMovieSet;
    }
}
