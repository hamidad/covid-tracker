import { Injectable } from '@angular/core';
import {
  DEFAULT_COUNTRY,
  COUNTRIES,
} from 'src/app/shared/external-apis/rapid-api/covid-19-data-api/constants/covid-19-data-api-constants';
import { Country } from 'src/app/shared/external-apis/rapid-api/covid-19-data-api/models/country.model';
import {
  SelectConfig,
  ISelectEntry,
  SelectEntry,
} from 'src/app/shared/forms/form-elements/select/models/select-config.model';

@Injectable({
  providedIn: 'root',
})
export class DashboardService {
  private selectCountryConfig: SelectConfig<ISelectEntry> = {
    selected: new SelectEntry(DEFAULT_COUNTRY?.code, DEFAULT_COUNTRY?.name),
    entries: COUNTRIES.map((country: Country) => {
      return new SelectEntry(country?.code, country?.name);
    }),
  };

  private defaultConfirmedCasesPerCountryChartOptions: any = {
    chart: {
      backgroundColor: 'transparent',
      plotBackgroundColor: undefined,
      plotBorderWidth: undefined,
      plotShadow: false,
      type: 'pie',
    },
    tooltip: {
      pointFormat:
        '{series.name}: <b> {point.y} </b> (<b>{point.percentage:.1f}%</b>)',
    },
    accessibility: {
      point: {
        valueSuffix: '%',
      },
    },
    title: {
      text: 'Countries - Confirmed cases',
    },
    credits: {
      enabled: false,
    },
    series: [
      {
        name: 'Confirmed cases',
        colorByPoint: true,
        data: [],
        dataLabels: {
          style: {
            textOutline: '',
          },
        },
      },
    ],
  };

  constructor() {}

  getSelectCountryConfig(): SelectConfig<ISelectEntry> {
    return this.selectCountryConfig;
  }

  getDefaultConfirmedCasesPerCountryChartOptions(): any {
    return this.defaultConfirmedCasesPerCountryChartOptions;
  }
}
