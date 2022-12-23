import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { PointRequest } from 'src/app/dto/PointRequest';
import { PointResponse } from 'src/app/dto/PointResponse';
import { PointService } from 'src/app/services/point.service';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-main-graph',
  templateUrl: './main-graph.component.html',
  styleUrls: ['./main-graph.component.css']
})
export class MainGraphComponent implements OnInit {
  points: PointResponse[] = [];

  pointsToDraw: PointResponse[] = [];

  errorMessage: string = "";

  r: number = -1;

  @Output() addEvent = new EventEmitter<PointResponse>();

  constructor(private pointService: PointService) {
  }

  ngOnInit() {
    this.pointService.getPoints().subscribe(data => {
      console.log(data);
      this.points = data;
    });
  }

  onClick(e: MouseEvent) {

    if (this.r != -1) {
      let target = document.getElementById("svg-graph")!.getBoundingClientRect();
      let x = Math.round(e.clientX - target?.left);
      let y = e.clientY - target?.top;
      let xForServer = ((x - 150) / (100 / this.r)).toFixed(3);
      let yForServer = ((y - 150 + window.scrollY) / (-100 / this.r)).toFixed(3);

      let request = new PointRequest(+xForServer, +yForServer, this.r);

      this.savePoint(request);
      // let coords = this.board.getUsrCoordsOfMouse(event);
      // let x = coords[0].toFixed(2);
      // let y = coords[1].toFixed(2);
      // let r = this.r
      // console.log(x + " " + y + " " + r);

      // let point = new PointRequest(+x, +y, r);
      // this.savePoint(point)
    } else {
      this.errorMessage = "You have to choose R"
    }
  }



  addPoint(point: PointResponse) {
    this.points.push(point);
    this.refresh(point.radius);
  }

  savePoint(point: PointRequest) {
    this.pointService.postPoint(point).subscribe({
      next: (data) => {
        console.log("New point " + data);
        this.addEvent.emit(data);
        this.addPoint(data);
      },
      error: (err) => {
        console.log(err);
      }
    });
  }

  refresh(r: number) {
    this.r = r;
    this.errorMessage = ''
    console.log("Graph: " + r);
    this.refreshPointsToDraw(r);
    console.log(this.pointsToDraw);
  }

  refreshPointsToDraw(r: number) {
    this.pointsToDraw = [];
    console.log("points: ", this.points);
    for (const point of this.points) {
      if (point.radius == r) {
        this.pointsToDraw.push(point);
      }

    }
  }

  createPoint(point: PointResponse) {
    let color = (point.hit ? "#7ce57c" : "#dc4a4a");
    // return this.board.create("point", [point.x, point.y], {
      // name: '', fixed: true, fillColor: color, fillOpacity: 1,
      // strokewidth: 0
    // });

  }

}