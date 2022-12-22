import { Component } from '@angular/core';

@Component({
  selector: 'app-main-table',
  templateUrl: './main-table.component.html',
  styleUrls: ['./main-table.component.css']
})
export class MainTableComponent {
  headers = ["x", "y", "radius", "hit", "creationDate", "workingTime"];
  rows: any[] = [];

  
}
