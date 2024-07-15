package org.example.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "account_profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int account;
    private String name;

    @OneToMany(mappedBy = "profile")
    private Set<WatchedMovie> watchedMovieSet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<WatchedMovie> getWatchedMovieSet() {
        return watchedMovieSet;
    }
}
