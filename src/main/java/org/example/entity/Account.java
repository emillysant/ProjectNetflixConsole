package org.example.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDate;

/**
 * Represents an account entity in the system.
 *
 * @author Leo Silva Souza
 * @since 1.0
 */
@Entity
@Table(name = "accounts")
@DynamicInsert
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;
    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "created_at")
    private LocalDate createdAt;

    /**
     * Returns the unique identifier of the account.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the account.
     *
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the email address associated with the account.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address associated with the account.
     *
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the hashed password of the account.
     *
     * @return the passwordHash
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Sets the hashed password of the account.
     *
     * @param password_hash the passwordHash to set
     */
    public void setPasswordHash(String password_hash) {
        this.passwordHash = password_hash;
    }

    /**
     * Returns the date when the account was created.
     *
     * @return the createdAt
     */
    public LocalDate getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the date when the account was created.
     *
     * @param created_at the createdAt to set
     */
    public void setCreatedAt(LocalDate created_at) {
        this.createdAt = created_at;
    }
}
