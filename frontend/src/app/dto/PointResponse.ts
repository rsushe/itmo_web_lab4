export class PointResponse {
    x: number;
    y: number;
    radius: number;
    hit: boolean;
    creationDate: Date;
    workingTime: number;

    constructor (_x: number, _y: number, _radius: number, _hit: boolean, _creationDate: Date, _workingTime: number) {
        this.x = _x;
        this.y = _y;
        this.radius = _radius;
        this.hit = _hit;
        this.creationDate = _creationDate;
        this.workingTime = _workingTime;
    }
}