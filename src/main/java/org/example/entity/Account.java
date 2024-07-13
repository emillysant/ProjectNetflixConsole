package org.example.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDate;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String password_hash) {
        this.passwordHash = password_hash;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate created_at) {
        this.createdAt = created_at;
    }
}
