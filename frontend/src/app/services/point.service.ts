import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PointRequest } from '../dto/PointRequest';
import { PointResponse } from '../dto/PointResponse';

const BASE_URI = "http://localhost:8080/api";
const POINTS_API = BASE_URI + '/points';
const POINT_API = BASE_URI + "/point";

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
    providedIn: 'root',
})
export class PointService {

    constructor(private http: HttpClient) { }

    getPoints(): Observable<PointResponse[]> {
        return this.http.get<PointResponse[]>(POINTS_API);
    }

    getPointsByRadius(radius: number): Observable<PointResponse[]> {
        let params: HttpParams = new HttpParams();
        params.set("pointRadius", radius);

        return this.http.get<PointResponse[]>(POINTS_API, { params: params });
    }

    deletePoints(): void {
        this.http.get<PointResponse[]>(POINTS_API).subscribe({
            error: (err) => {
                console.log(err);
            }
        });
    }

    postPoint(pointRequest: PointRequest): Observable<PointResponse> {
        return this.http.post<PointResponse>(POINT_API, pointRequest, httpOptions);
    }
}