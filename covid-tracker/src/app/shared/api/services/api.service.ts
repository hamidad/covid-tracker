import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  constructor(protected httpClient: HttpClient) {}

  get<T>(
    url: string,
    requestName?: string,
    headers?: HttpHeaders,
    params?: HttpParams
  ): Observable<T> {
    return this.httpClient
      .get<T>(url, {
        headers,
        params,
      })
      .pipe(
        catchError((error) => {
          return this.handleError<T>(requestName, error);
        })
      );
  }

  protected handleError<T>(operation = 'operation', error?: T) {
    console.log(`${operation} failed.`);

    console.error(error);

    return throwError(error);
  }
}
