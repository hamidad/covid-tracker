import { HttpClientModule, HttpHeaders } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { environment } from 'src/environments/environment';
import { Covic19DataApiResponseError } from '../models/error-response.model';

import { Covid19DataApiService } from './covid-19-data-api.service';

describe('Covid19DataApiService', () => {
  let httpClientSpy: { get: jasmine.Spy };
  let service: Covid19DataApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientModule],
      providers: [HttpClientModule],
    });
    httpClientSpy = jasmine.createSpyObj('HttpClient', ['get']);
    service = TestBed.inject(Covid19DataApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should return an error when the server returns a 403 (API_KEY is not correct)', (done: DoneFn) => {
    service.getDefaultHttpHeaders = () => {
      return new HttpHeaders({
        'Content-Type': 'application/json',
        'x-rapidapi-key': 'WRONG_API_KEY',
        'x-rapidapi-host': environment.covid19ApiHost,
      });
    };
    const errorResponse: Covic19DataApiResponseError = {
      message: 'You are not subscribed to this API.',
    };

    httpClientSpy.get.and.returnValue(errorResponse);

    service.getLatestCountryDataByCode('it').subscribe(
      (data) => done.fail('expected an 403 error, not success data'),
      (error) => {
        expect(error.error.message).toContain(
          'You are not subscribed to this API.'
        );
        done();
      }
    );
  });
});
