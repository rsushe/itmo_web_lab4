package com.rsushe.weblab4.service;

import com.rsushe.weblab4.dto.PointRequest;
import com.rsushe.weblab4.dto.PointResponse;

import java.util.List;
import java.util.Optional;

public interface PointService {
    List<PointResponse> getUserPoints(Optional<Double> pointRadius, Long userId);

    void deleteUserPoints(Long userId);

    PointResponse addPointToUser(PointRequest pointRequest, long userId);
}
