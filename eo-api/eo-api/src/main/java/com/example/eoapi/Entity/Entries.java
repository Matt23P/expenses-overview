package com.example.eoapi.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "entries")
public class Entries {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "userid")
    private int userId;

    @Column(name = "amount")
    private double amount;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String type;

    @Column(name = "income")
    private boolean income;

    @Column(name = "currency")
    private String currency;

    @Column(name = "year")
    private String year;

    @Column(name = "month")
    private String month;

    @Column(name = "day")
    private String day;
}
