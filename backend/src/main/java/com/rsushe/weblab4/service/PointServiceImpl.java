package com.rsushe.weblab4.service;

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
    public List<PointResponse> getUserPoints(Double pointRadius, Integer timezone, Long userId) {
        List<Point> optionalPointResponses = pointRepository.getPointsByRadiusAndUserId(pointRadius, userId);

        return optionalPointResponses
                .stream()
                .map(pointToPointResponseConverter::convert)
                .peek(pointResponse -> updatePointResponseCreationDate(pointResponse, timezone))
                .toList();
    }

    private void updatePointResponseCreationDate(PointResponse pointResponse, Integer timezone) {
        pointResponse.setCreationDate(pointResponse.getCreationDate().minusHours(timezone / 60));
    }

    @Override
    public void deleteUserPoints(Long userId) {
        pointRepository.deletePointsByUserId(userId);
    }

    @Override
    public PointResponse addPointToUser(PointRequest pointRequest, long userId) {
        Point newPoint = pointRequestToPointConverter.convert(pointRequest);
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException(); //TODO is it possible?
        }
        newPoint.setUser(optionalUser.get());

        Point newTablePoint = pointRepository.save(newPoint);
        return pointToPointResponseConverter.convert(newTablePoint);
    }
}
