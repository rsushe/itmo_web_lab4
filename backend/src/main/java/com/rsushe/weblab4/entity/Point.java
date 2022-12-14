package com.rsushe.weblab4.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.Builder;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "point")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Point {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private double x;

    @Column(nullable = false)
    private double y;

    @Column(nullable = false)
    private double radius;

    @Column(nullable = false)
    private boolean hit;

    @Column(nullable = false)
    private LocalDateTime creationDate;

    @Column(nullable = false)
    private Instant workingTime;
}
