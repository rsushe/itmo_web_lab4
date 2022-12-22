import { Component } from '@angular/core';
import { FormBuilder } from "@angular/forms";
import { PointService } from 'src/app/services/point.service';
import { PointRequest } from 'src/app/dto/PointRequest';


@Component({
  selector: 'app-main-form',
  templateUrl: './main-form.component.html',
  styleUrls: ['./main-form.component.css']
})
export class MainFormComponent {

  pointForm = this.formBuilder.group({
    x: '',
    y: '',
    radius: '',
    timezone: ''
  });

  errorMessage: string = "";

  x_values: number[] = [-4, -3, -2, -1, 0, 1, 2, 3, 4];
  y_min_value: number = -5;
  y_max_value: number = 5;
  r_values: number[] = [-4, -3, -2, -1, 0, 1, 2, 3, 4];
  rows: any[] = [];
  headers = ["x", "y", "radius", "hit", "creationDate", "workingTime"];

  constructor(private pointService: PointService, private formBuilder: FormBuilder) {
      
  }

  

  onSubmit(): void {
    this.errorMessage = "";

    if (this.pointForm.value.x === '' || this.pointForm.value.x == null) {
      this.errorMessage = "X value is required";
    } else if (this.pointForm.value.y === '' || this.pointForm.value.y == null) {
      this.errorMessage = "Y value is required";
    } else if (this.pointForm.value.radius === '' || this.pointForm.value.radius == null) {
      this.errorMessage = "R value is required";
    } else {

      console.log('Form data: ', this.pointForm.value);

      let point: PointRequest = new PointRequest(
        +this.pointForm.value.x,
        +this.pointForm.value.y,
        +this.pointForm.value.radius
      );


      this.pointService.postPoint(point).subscribe({
        next: (data) => {
          console.log("form data after request: ", data);
          data.creationDate = new Date(data.creationDate);
          this.rows.push(data);
          this.pointForm.reset();
        },
        error: (err) => {
          console.log(err);
        }
      });

    }
  }

  rChange(event: any) {
    if (event.target.value != '') {
      let r = event.target.value.split(": ")[1];
      console.log(r);

      this.pointService.getPointsByRadius(+r).subscribe({
        next: (data) => {
          console.log(data);
          this.rows = data;
        },
        error: (err) => {
          console.log(err);
        }
      })
    }
  }
}
