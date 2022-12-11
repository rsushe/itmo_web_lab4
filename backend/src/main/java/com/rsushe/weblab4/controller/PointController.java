package com.rsushe.weblab4.controller;

import com.rsushe.weblab4.dto.PointRequest;
import com.rsushe.weblab4.dto.PointResponse;
import com.rsushe.weblab4.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PointController {

    private final PointService pointService;

    @Autowired
    public PointController(PointService pointService) {
        this.pointService = pointService;
    }

    @GetMapping("/points")
    public ResponseEntity<List<PointResponse>> getUserPoints(@RequestParam Double pointRadius, @RequestParam Integer timezone) {
        return ResponseEntity.ok(pointService.getUserPoints(pointRadius, timezone, 0L)); //TODO user authentication
    }

    @DeleteMapping("/points")
    public void deleteUserPoints() {
        pointService.deleteUserPoints(0L); //TODO user authentication
    }

    @PostMapping("/point")
    public ResponseEntity<PointResponse> addPointToUser(@RequestBody PointRequest pointRequest) {
        return ResponseEntity.ok(pointService.addPointToUser(pointRequest, 0)); //TODO user authentication
    }
}
