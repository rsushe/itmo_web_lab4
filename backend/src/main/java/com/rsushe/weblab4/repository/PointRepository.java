package com.rsushe.weblab4.repository;

import com.rsushe.weblab4.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {
    List<Point> getPointsByRadiusAndUserId(Double radius, Long userId);

    void deletePointsByUserId(Long userId);
}
