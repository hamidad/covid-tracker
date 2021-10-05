import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';

import { DEFAULT_COUNTRY } from 'src/app/shared/external-apis/rapid-api/covid-19-data-api/constants/covid-19-data-api-constants';
import { LatestCountryData } from 'src/app/shared/external-apis/rapid-api/covid-19-data-api/models/latest-country-data.model';
import { Covid19DataApiService } from 'src/app/shared/external-apis/rapid-api/covid-19-data-api/services/covid-19-data-api.service';
import {
  ISelectEntry,
  SelectConfig,
  SelectEntry,
} from 'src/app/shared/forms/form-elements/select/models/select-config.model';
import { isDefined } from 'src/app/shared/utils/shared-utils';
import { DashboardService } from '../services/dashboard.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
})
export class DashboardComponent implements OnInit, OnDestroy {
  confirmedCasesPerCountryChartOptions: any =
    this.dashboardService.getDefaultConfirmedCasesPerCountryChartOptions();

  selectCountryConfig: SelectConfig<ISelectEntry> =
    this.dashboardService.getSelectCountryConfig();

  private componentDestroyed$: Subject<boolean> = new Subject();

  constructor(
    private covid19DataApiService: Covid19DataApiService,
    private dashboardService: DashboardService
  ) {}

  ngOnInit(): void {
    this.getLatestCountryDataByCode(DEFAULT_COUNTRY?.code);
  }

  ngOnDestroy(): void {
    this.componentDestroyed$.next(true);
    this.componentDestroyed$.complete();
  }

  addDataPoint(x: string, y: number) {
    this.confirmedCasesPerCountryChartOptions?.series[0]?.data.push([x, y]);
    this.confirmedCasesPerCountryChartOptions = {
      ...this.confirmedCasesPerCountryChartOptions,
    };
  }

  onSelectionChange(event: SelectEntry): void {
    this.selectCountryConfig.selected = event;
    this.getLatestCountryDataByCode(event?.value);
  }

  getLatestCountryDataByCode(code: string): void {
    if (!isDefined(code)) {
      return;
    }

    this.covid19DataApiService
      .getLatestCountryDataByCode(code)
      .pipe(takeUntil(this.componentDestroyed$))
      .subscribe((response: LatestCountryData) => {
        if (isDefined(response)) {
          this.doActionOnGetLatestCountryDataByCodeSuccess(response);
        }
      });
  }

  private doActionOnGetLatestCountryDataByCodeSuccess(
    latestCountryData: LatestCountryData
  ): void {
    this.addDataPoint(
      latestCountryData?.country,
      latestCountryData?.confirmed
    );
  }
}
