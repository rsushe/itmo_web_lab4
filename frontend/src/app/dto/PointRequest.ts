export class PointRequest {
    x: number;
    y: number;
    radius: number;

    constructor (_x: number, _y: number, _radius: number) {
        this.x = _x;
        this.y = _y;
        this.radius = _radius;
    }
}