# Covid Tracker is a web based app that aims to display coronavirus statistics data visually using external resources

It is a __playaround project__ for training and exercising project using [Angular](https://angular.io/), [Spring Boot](https://spring.io/projects/spring-boot), [Rapid API](https://rapidapi.com/) and [Highcharts](https://www.highcharts.com/).


Visualization of data is generally revolving about statistical data by country or more countries. Country data consists of basics coronavirus statistics like: 
- recovered (cases)
- critical
- deaths
- dates (of last change and/or update)
- latitude
- longitude etc.

Data is not fake, but fetched from external resource - [Rapid API](https://rapidapi.com/), but it could be saved in DB in case external one is not available, so it can be used (if it is existing/saved before).

## Code examples

### Angular / Typescript
```typescript
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

  ...
}
```

### Spring Boot / Java
```java
public LatestCountryData getLatestCountryDataByCodeExternally(String code) throws Covid19Exception {
        try {
            RapidApiHttpHeadersBuilder rapidApiHttpHeadersBuilder = new RapidApiHttpHeadersBuilder()
                    .createHttpHeaders()
                    .setHttpHeadersAcceptApplicationJson()
                    .setHttpHeadersKey()
                    .setHttpHeadersHost();

            UriComponentsBuilder builder = UriComponentsBuilder
                    .fromHttpUrl(LatestCountryDataConstants.RAPID_API_URL)
                    .queryParam("code", code);

            HttpEntity<String> entity = new HttpEntity<>("body", rapidApiHttpHeadersBuilder.getHttpHeaders());

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<LatestCountryData[]> response = restTemplate.exchange(
                    builder.toUriString(),
                    HttpMethod.GET,
                    entity,
                    LatestCountryData[].class);

            LatestCountryData[] result = response.getBody();

            return result.length > 0 ? result[result.length - 1] : null;
        } catch (HttpClientErrorException e) {
            HttpStatus statusCode = e.getStatusCode();
            throw new Covid19Exception(statusCode, e.getMessage());
        }  catch(Exception e) {
            throw new Covid19Exception(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
```

