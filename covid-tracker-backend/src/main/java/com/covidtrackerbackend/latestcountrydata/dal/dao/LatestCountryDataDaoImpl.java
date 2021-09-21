package com.covidtrackerbackend.latestcountrydata.dal.dao;

import com.covidtrackerbackend.latestcountrydata.constants.LatestCountryDataConstants;
import com.covidtrackerbackend.latestcountrydata.dal.entity.LatestCountryData;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

@Repository
public class LatestCountryDataDaoImpl implements LatestCountryDataDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<LatestCountryData> getAll() {
        List<LatestCountryData> latestCountryDataList =
                entityManager.createQuery("SELECT DISTINCT c FROM LatestCountryData AS c").getResultList();

        return  latestCountryDataList;
    }

    @Override
    public LatestCountryData getLatestCountryDataByCode(String code) {
        LatestCountryData latestCountryData = getLatestCountryDataByCodeFromDB(code);

        if (latestCountryData == null) {
            latestCountryData = getLatestCountryDataByCodeExternally(code);
        }

        return latestCountryData;
    }

    @Override
    public LatestCountryData getLatestCountryDataByCodeFromDB(String code) {
        String queryString = "SELECT DISTINCT c FROM LatestCountryData AS c WHERE c.code = :code";

        TypedQuery<LatestCountryData> query = entityManager.createQuery(queryString, LatestCountryData.class);
        query.setParameter("code", code);

        List<LatestCountryData> resultList = query.getResultList();

        return !resultList.isEmpty() ? resultList.get(0) : null;
    }

    @Override
    public LatestCountryData getLatestCountryDataByCodeExternally(String code) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("x-rapidapi-key", LatestCountryDataConstants.RAPID_API_KEY);
        headers.set("x-rapidapi-host", LatestCountryDataConstants.RAPID_API_HOST);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(LatestCountryDataConstants.RAPID_API_URL)
                .queryParam("code", code);

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<LatestCountryData[]> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                LatestCountryData[].class);

        LatestCountryData[] result = response.getBody();

        return result.length > 0 ? result[result.length - 1] : null;
    }
}
