package com.example.eoapi.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "months")
public class Months {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "userid")
    private int userId;

    @Column(name = "yearid")
    private int yearId;

    @Column(name = "month")
    private String month;
}
