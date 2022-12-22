import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MainGraphComponent } from './main-graph.component';

describe('MainGraphComponent', () => {
  let component: MainGraphComponent;
  let fixture: ComponentFixture<MainGraphComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MainGraphComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MainGraphComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
