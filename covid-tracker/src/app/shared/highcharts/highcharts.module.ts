import { NgModule } from '@angular/core';
import { HighchartsChartModule } from 'highcharts-angular';
import { HIGHCHARTS_CHART_COMPONENTS } from './highcharts-chart';

@NgModule({
  declarations: [HIGHCHARTS_CHART_COMPONENTS],
  imports: [HighchartsChartModule],
  exports: [HighchartsChartModule, HIGHCHARTS_CHART_COMPONENTS],
})
export class HighchartsModule {}
