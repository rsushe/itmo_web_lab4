package com.rsushe.weblab4.converter;

import com.rsushe.weblab4.dto.PointRequest;
import com.rsushe.weblab4.entity.Point;
import com.rsushe.weblab4.util.HitChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class PointRequestToPointConverter implements Converter<PointRequest, Point> {

    private final HitChecker hitChecker;

    @Autowired
    public PointRequestToPointConverter(HitChecker hitChecker) {
        this.hitChecker = hitChecker;
    }

    @Override
    public Point convert(PointRequest pointRequest) {
        double x = pointRequest.getX(), y = pointRequest.getY(), radius = pointRequest.getRadius();
        return Point
                .builder()
                .x(x)
                .y(y)
                .radius(radius)
                .hit(hitChecker.isPointHit(x, y, radius))
                .creationDate(LocalDateTime.now(ZoneOffset.UTC))
                .build();
    }
}
