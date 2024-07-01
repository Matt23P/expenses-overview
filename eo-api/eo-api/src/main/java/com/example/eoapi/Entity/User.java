package com.example.eoapi.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", length = 32, nullable = false)
    private String password;

    @Column(name = "username", length = 32, nullable = false)
    private String username;

    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @Column(name = "user_currency", length = 3, nullable = false)
    private String userCurrency;

    public User(String email, String password, String username, LocalDate now, String userCurrency) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.creationDate = now;
        this.userCurrency = userCurrency;
    }

}
