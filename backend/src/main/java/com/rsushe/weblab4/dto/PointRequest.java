package com.rsushe.weblab4.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PointRequest {
    private double x;
    private double y;
    private double radius;
    private int timezone;
}
