import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HighchartsChartComponent } from './highcharts-chart.component';

describe('HighchartsChartComponent', () => {
  let component: HighchartsChartComponent;
  let fixture: ComponentFixture<HighchartsChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [HighchartsChartComponent],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HighchartsChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
