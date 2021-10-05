import { HttpClientModule } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { Covid19DataApiService } from './covid-19-data-api.service';

describe('Covid19DataApiService', () => {
  let service: Covid19DataApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientModule],
      providers: [HttpClientModule],
    });
    service = TestBed.inject(Covid19DataApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
