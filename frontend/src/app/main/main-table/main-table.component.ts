import { Component, OnInit } from '@angular/core';
import { PointResponse } from 'src/app/dto/PointResponse';
import { PointService } from 'src/app/services/point.service';

@Component({
  selector: 'app-main-table',
  templateUrl: './main-table.component.html',
  styleUrls: ['./main-table.component.css']
})
export class MainTableComponent implements OnInit {
  headers = ["x", "y", "radius", "hit", "creationDate", "workingTime"];
  rows: any[] = [];

  constructor(private pointService: PointService) {
  
  }

  ngOnInit(): void {
    this.pointService.getPoints().subscribe(data => {
      console.log(data);
      this.rows = data;
    });
  }

  addPoint(point:PointResponse) {


    this.rows.push(point);
    // this.table.renderRows();
  }
}
