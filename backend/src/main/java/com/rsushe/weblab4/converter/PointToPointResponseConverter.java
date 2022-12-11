package com.rsushe.weblab4.converter;

import com.rsushe.weblab4.dto.PointResponse;
import com.rsushe.weblab4.entity.Point;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PointToPointResponseConverter implements Converter<Point, PointResponse> {

    @Override
    public @NonNull PointResponse convert(Point point) {
        return PointResponse
                .builder()
                .x(point.getX())
                .y(point.getY())
                .radius(point.getRadius())
                .hit(point.isHit())
                .creationDate(point.getCreationDate())
                .workingTime(point.getWorkingTime())
                .build();
    }
}
