package com.rsushe.weblab4.dto;

import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PointResponse {
    private double x;
    private double y;
    private double radius;
    private boolean hit;
    private LocalDateTime creationDate;
    private Instant workingTime;
}
