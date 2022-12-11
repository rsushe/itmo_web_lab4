package com.rsushe.weblab4.entity;

import jakarta.persistence.*;
import lombok.*;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="userId", referencedColumnName = "id")
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
