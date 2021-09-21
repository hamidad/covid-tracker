package com.covidtrackerbackend.latestcountrydata.service;

import com.covidtrackerbackend.latestcountrydata.dal.entity.LatestCountryData;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LatestCountryDataService {
    public List<LatestCountryData> findAll();

    public LatestCountryData getLatestCountryDataByCode(String code);
}
