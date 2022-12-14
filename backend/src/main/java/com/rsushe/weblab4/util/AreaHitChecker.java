package com.rsushe.weblab4.util;

import org.springframework.stereotype.Component;

@Component
public class AreaHitChecker implements HitChecker {

    @Override
    public boolean isPointHit(double x, double y, double r) {
        return isHitCircle(x, y, r) || isHitRectangle(x, y, r) || isHitTriangle(x, y, r);
    }

    private boolean isHitCircle(double x, double y, double r) {
        return x <= 0 && y <= 0 && (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2));
    }

    private boolean isHitRectangle(double x, double y, double r) {
        return x <= 0 && x >= -r && y >= 0 && y <= r / 2;
    }

    private boolean isHitTriangle(double x, double y, double r) {
        return x >= 0 && y >= 0 && y + x <= r / 2;
    }
}
