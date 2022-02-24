package com.covidtrackerbackend.dailycountryreport.dal.dao;

import com.covidtrackerbackend.dailycountryreport.constants.DailyReportByCountryConstants;
import com.covidtrackerbackend.dailycountryreport.dal.model.DailyReportByCountry;
import com.covidtrackerbackend.exceptions.Covid19Exception;
import com.covidtrackerbackend.utils.rapidapi.RapidApiHttpHeadersBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Repository
public class DailyReportByCountryDaoImpl implements DailyReportByCountryDao {

    @Override
    public DailyReportByCountry getDailyReportByCountryCode(String code, String date)
            throws Covid19Exception {
       return getDailyReportByCountryCodeExternally(code, date);
    }

    @Override
    public DailyReportByCountry getDailyReportByCountryCodeExternally(String code, String date)
            throws Covid19Exception {
        try {
            RapidApiHttpHeadersBuilder rapidApiHttpHeadersBuilder = new RapidApiHttpHeadersBuilder()
                    .createHttpHeaders()
                    .setHttpHeadersAcceptApplicationJson()
                    .setHttpHeadersKey()
                    .setHttpHeadersHost();

            UriComponentsBuilder builder = UriComponentsBuilder
                    .fromHttpUrl(DailyReportByCountryConstants.RAPID_API_URL)
                    .queryParam("code", code)
                    .queryParam("date", date);

            HttpEntity<String> entity = new HttpEntity<>("body", rapidApiHttpHeadersBuilder.getHttpHeaders());

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<DailyReportByCountry[]> response = restTemplate.exchange(
                    builder.toUriString(),
                    HttpMethod.GET,
                    entity,
                    DailyReportByCountry[].class);

            DailyReportByCountry[] result = response.getBody();

            return result.length > 0 ? result[result.length - 1] : null;
        } catch (HttpClientErrorException e) {
            HttpStatus statusCode = e.getStatusCode();
            throw new Covid19Exception(statusCode, e.getMessage());
        }  catch(Exception e) {
            throw new Covid19Exception(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
