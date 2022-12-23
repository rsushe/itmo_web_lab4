package com.rsushe.weblab4.service;

import com.rsushe.weblab4.audited.Audited;
import com.rsushe.weblab4.converter.PointRequestToPointConverter;
import com.rsushe.weblab4.converter.PointToPointResponseConverter;
import com.rsushe.weblab4.dto.PointRequest;
import com.rsushe.weblab4.dto.PointResponse;
import com.rsushe.weblab4.entity.Point;
import com.rsushe.weblab4.entity.User;
import com.rsushe.weblab4.repository.PointRepository;
import com.rsushe.weblab4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PointServiceImpl implements PointService {

    private final PointRepository pointRepository;
    private final UserRepository userRepository;
    private final PointToPointResponseConverter pointToPointResponseConverter;
    private final PointRequestToPointConverter pointRequestToPointConverter;

    @Autowired
    public PointServiceImpl(PointRepository pointRepository, UserRepository userRepository, PointToPointResponseConverter pointToPointResponseConverter, PointRequestToPointConverter pointRequestToPointConverter) {
        this.pointRepository = pointRepository;
        this.userRepository = userRepository;
        this.pointToPointResponseConverter = pointToPointResponseConverter;
        this.pointRequestToPointConverter = pointRequestToPointConverter;
    }

    @Override
    @Audited
    public List<PointResponse> getUserPoints(Optional<Double> pointRadius, Long userId) {
        List<Point> points;
        if (pointRadius.isEmpty()) points = pointRepository.getPointsByUserId(userId);
        else points = pointRepository.getPointsByRadiusAndUserId(pointRadius.get(), userId);

        return points
                .stream()
                .map(pointToPointResponseConverter::convert)
                .toList();
    }

    @Override
    public void deleteUserPoints(Long userId) {
        pointRepository.deletePointsByUserId(userId);
    }

    @Override
    public PointResponse addPointToUser(PointRequest pointRequest, long userId) {
        long currentTime = System.currentTimeMillis();

        Point newPoint = pointRequestToPointConverter.convert(pointRequest);
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException(); //TODO is it possible?
        }
        newPoint.setUser(optionalUser.get());
        newPoint.setWorkingTime((System.currentTimeMillis() - currentTime) / 1000.0d);

        Point newTablePoint = pointRepository.save(newPoint);
        return pointToPointResponseConverter.convert(newTablePoint);
    }
}
