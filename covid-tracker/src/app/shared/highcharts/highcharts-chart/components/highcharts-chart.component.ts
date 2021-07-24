import {
  Component,
  Input,
  OnChanges,
  OnInit,
  SimpleChanges,
} from '@angular/core';
import * as Highcharts from 'highcharts';

@Component({
  selector: 'app-highcharts-chart',
  templateUrl: './highcharts-chart.component.html',
  styleUrls: ['./highcharts-chart.component.scss'],
})
export class HighchartsChartComponent implements OnInit, OnChanges {
  /**
   * Required.
   * Chart options
   */
  @Input()
  chartOptions: Highcharts.Options | any = {};

  @Input()
  updateFlag = false;

  Highcharts: typeof Highcharts = Highcharts;

  constructor() {}

  ngOnChanges(changes: SimpleChanges): void {
    if (changes?.chartOptions) {
      this.updateFlag = true;
    }
  }

  ngOnInit(): void {}
}
