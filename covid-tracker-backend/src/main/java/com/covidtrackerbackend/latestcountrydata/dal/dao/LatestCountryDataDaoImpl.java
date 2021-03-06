package com.covidtrackerbackend.latestcountrydata.dal.dao;

import com.covidtrackerbackend.constants.RapidApiConstants;
import com.covidtrackerbackend.exceptions.Covid19Exception;
import com.covidtrackerbackend.latestcountrydata.constants.LatestCountryDataConstants;
import com.covidtrackerbackend.latestcountrydata.dal.entity.LatestCountryData;
import com.covidtrackerbackend.utils.rapidapi.RapidApiHttpHeadersBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

@Repository
public class LatestCountryDataDaoImpl implements LatestCountryDataDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<LatestCountryData> findAll() {
        List<LatestCountryData> latestCountryDataList = entityManager
                .createQuery("SELECT DISTINCT c FROM LatestCountryData AS c")
                .getResultList();

        return  latestCountryDataList;
    }

    @Override
    public LatestCountryData getLatestCountryDataByCode(String code) throws Covid19Exception {
        LatestCountryData latestCountryData = getLatestCountryDataByCodeFromDB(code);

        if (latestCountryData == null) {
            latestCountryData = getLatestCountryDataByCodeExternally(code);
        }

        return latestCountryData;
    }

    @Override
    public LatestCountryData getLatestCountryDataByCodeFromDB(String code) {
        String queryString = "SELECT DISTINCT c FROM LatestCountryData AS c WHERE c.code = :code";

        List<LatestCountryData> resultList = entityManager
                .createQuery(queryString, LatestCountryData.class)
                .setParameter("code", code)
                .getResultList();

        return !resultList.isEmpty() ? resultList.get(0) : null;
    }

    @Override
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
            throw new Covid19Exception(HttpStatus.INTERNAL_SERVER_ERROR ,e.getMessage());
        }
    }
}
