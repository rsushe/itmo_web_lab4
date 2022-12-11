package com.rsushe.weblab4.converter;

import com.rsushe.weblab4.dto.PointRequest;
import com.rsushe.weblab4.entity.Point;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class PointRequestToPointConverter implements Converter<PointRequest, Point> {

    @Override
    public Point convert(PointRequest pointRequest) {
        return Point
                .builder()
                .x(pointRequest.getX())
                .y(pointRequest.getY())
                .radius(pointRequest.getRadius())
                .creationDate(LocalDateTime.now(ZoneOffset.UTC))
                .build();
    }
}
