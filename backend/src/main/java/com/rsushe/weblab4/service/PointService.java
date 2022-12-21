package com.rsushe.weblab4.service;

import com.rsushe.weblab4.dto.PointRequest;
import com.rsushe.weblab4.dto.PointResponse;

import java.util.List;

public interface PointService {
    List<PointResponse> getUserPoints(Double pointRadius, Long userId);

    void deleteUserPoints(Long userId);

    PointResponse addPointToUser(PointRequest pointRequest, long userId);
}
