import {
  HttpClientTestingModule,
  HttpTestingController,
} from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { environment } from 'src/environments/environment';
import { LatestCountryData } from '../models/latest-country-data.model';

import { Covid19DataApiService } from './covid-19-data-api.service';

describe('Covid19DataApiService', () => {
  let service: Covid19DataApiService;
  let controller: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [Covid19DataApiService],
    });
    service = TestBed.inject(Covid19DataApiService);
    controller = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should call getLatestCountryDataByCode and return appropriate country data on success', () => {
    const COUNTRY_CODE = 'fake_code';
    const EXPECTED_URL = `${environment.backendApi}/country/code?code=${COUNTRY_CODE}`;

    service
      .getLatestCountryDataByCode(COUNTRY_CODE)
      .subscribe((latestCountryData: LatestCountryData) => {
        expect(latestCountryData).toEqual(new MockedLatestCountryData());
      });

    const request = controller.expectOne(EXPECTED_URL);
    request.flush(new MockedLatestCountryData());
  });
});

class MockedLatestCountryData implements LatestCountryData {
  country = 'BiH';
  code = 'BA';
  confirmed = 0;
  recovered = 0;
  critical = 0;
  deaths = 0;
  latitude = 0;
  longitude = 0;
  lastChange = '';
  lastUpdate = '';
}
