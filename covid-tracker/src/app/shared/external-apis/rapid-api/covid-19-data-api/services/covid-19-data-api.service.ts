import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from 'src/app/shared/api/services/api.service';
import { environment } from 'src/environments/environment';
import { LatestCountryData } from '../models/latest-country-data.model';

@Injectable({
  providedIn: 'root',
})
export class Covid19DataApiService extends ApiService {
  constructor(protected httpClient: HttpClient) {
    super(httpClient);
  }

  getLatestCountryDataByCode(code: string): Observable<LatestCountryData> {
    const headers = this.getDefaultHttpHeaders();
    const params = this.getCodeHttpParams(code);

    return this.get<LatestCountryData>(
      `${environment.backendApi}/country/code`,
      'getLatestCountryDataByCode',
      headers,
      params
    );
  }

  getDefaultHttpHeaders(): HttpHeaders {
    return new HttpHeaders({
      'Content-Type': 'application/json',
    });
  }

  private getCodeHttpParams(code: string): HttpParams {
    return new HttpParams().append('code', code);
  }
}
