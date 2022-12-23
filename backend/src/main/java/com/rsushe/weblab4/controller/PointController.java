package com.rsushe.weblab4.controller;

import com.rsushe.weblab4.dto.PointRequest;
import com.rsushe.weblab4.dto.PointResponse;
import com.rsushe.weblab4.security.AuthenticationToken;
import com.rsushe.weblab4.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PointController {

    private final PointService pointService;

    @Autowired
    public PointController(PointService pointService) {
        this.pointService = pointService;
    }

    @GetMapping("/points")
    public ResponseEntity<List<PointResponse>> getUserPoints(@RequestParam Optional<Double> pointRadius, @AuthenticationPrincipal Long userId) {

        return ResponseEntity.ok(pointService.getUserPoints(pointRadius, userId));
    }

    @DeleteMapping("/points")
    public void deleteUserPoints(@AuthenticationPrincipal Long userId) {
        pointService.deleteUserPoints(userId);
    }

    @PostMapping("/point")
    public ResponseEntity<PointResponse> addPointToUser(@RequestBody PointRequest pointRequest, @AuthenticationPrincipal Long userId) {
        return ResponseEntity.ok(pointService.addPointToUser(pointRequest, userId));
    }
}
